import java.io.File;
import java.util.*;



public class Day8A {

    public static final int INSTRUCTION_COUNT = 626;
    public static final int NOP = 0;
    public static final int ACC = 1;
    public static final int JMP = 2;

    public static void main(String Args[]) {
        Scanner input;
        String line = "";

        try {
            input = new Scanner(new File("instructions.txt"));
        } catch (Exception e) {
            return;
        }

        int acc = 0;
        byte[] instr = new byte[INSTRUCTION_COUNT];
        int[] value = new int[INSTRUCTION_COUNT];
        boolean[] ran = new boolean[INSTRUCTION_COUNT]; //all 0
        String[] substrings;

        for (int index = 0; input.hasNext(); index++) {
            line = input.nextLine();
            substrings = line.split(" ");
            value[index] = Integer.parseInt(substrings[1]);
            switch(substrings[0]){
                case "nop":
                    instr[index] = NOP;
                    break;
                case "acc":
                    instr[index] = ACC;
                    break;
                case "jmp":
                    instr[index] = JMP;
                    break;
            }
        }

        //first try it
        System.out.println(test(instr,ran,value,true));

        for (int i = 0; i < INSTRUCTION_COUNT; i++) {
            //change this instruction
            //System.out.println("evaluating instruction " + i);
            if (instr[i] == NOP) {
                //System.out.println("changing nop");
                instr[i] = JMP;
            } else if (instr[i] == JMP) {
                //System.out.println("changing jmp");
                instr[i] = NOP;
            } else {
                //System.out.println("changing nothing");
                continue;
            }
            //reset vals
            ran = new boolean[INSTRUCTION_COUNT];
            try {
                acc = test(instr, ran, value,false);
                break;
            } catch (NullPointerException e) {  }

            //change it back
            if (instr[i] == JMP) {
                instr[i] = NOP;
            } else if (instr[i] == NOP) {
                instr[i] = JMP;
            }
        }


        System.out.println(acc);
    }

    public static Integer test(byte[] instr, boolean[] ran, int[] value, boolean returnAlways){
        int pc = 0;
        int acc = 0;
        while(true) {
            //System.out.println("line: "+ (pc+1) + " instruction: " + instr[pc] + " val: " +value[pc]);
            if (ran[pc]) {
                //hit loop
                //System.out.println("hit loop on instruction: "+ pc);
                if (!returnAlways) {
                    return null;
                } else {
                    return acc;
                }
            }
            ran[pc] = true;
            if (instr[pc] == 1) {
                acc+=value[pc];
                pc++;
            } else if (instr[pc] == 2) {
                pc+=value[pc];
            } else {
                pc++;
            }
            if (pc == instr.length) {
                return acc;
            }
        }
    }
}