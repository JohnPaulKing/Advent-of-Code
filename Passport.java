public class Passport {
    int byr,iyr,eyr,cid;
    String hgt,hcl,ecl,pid;
    boolean valid;

    //build passport from string of text
    public Passport(String text) {
        byr=iyr=eyr=cid = 0;
        valid = true;
        //split into pairs
        String[] datos = text.split(" ");
        for (String pair : datos) {
            //split up pair
            String[] pairs = pair.split(":");
            switch (pairs[0]) {
                case "byr":
                    //first check for 4 digits
                    if (pairs[1].length() != 4) {
                        valid = false;
                        System.out.print("hgt length  ");
                    }
                    byr = Integer.parseInt(pairs[1]);
                    if (byr < 1920 || byr > 2002) {
                        valid = false;
                        System.out.print("byr range ");
                    }
                    break;
                case "iyr":
                    if (pairs[1].length() != 4) {
                        valid = false;
                        System.out.print("iyr length ");
                    }
                    iyr = Integer.parseInt(pairs[1]);
                    if (iyr < 2010 || iyr > 2020) {
                        valid = false;
                        System.out.print("iyr range ");
                    }
                    break;
                case "eyr":
                    if (pairs[1].length() != 4) {
                        valid = false;
                        System.out.print("eyr length ");
                    }
                    eyr = Integer.parseInt(pairs[1]);
                    if (eyr < 2020 || eyr > 2030) {
                        valid = false;
                        System.out.print("eyr range ");
                    }
                    break;
                case "pid":
                    if (pairs[1].length() != 9) {
                        System.out.print("pid length ");
                        valid = false;
                    }
                    pid = pairs[1];
                    try {
                        Long.parseLong(pid);
                    } catch (NumberFormatException e) {
                        valid = false; //not number format
                        System.out.print("pid format ");
                    }
                    break;
                case "cid":
                    cid = Integer.parseInt(pairs[1]);
                    break;
                case "hgt":
                    hgt = pairs[1];
                    String format = hgt.substring(hgt.length()-2);

                    int height;
                    if (format.equals("in")) {
                        height = Integer.parseInt(hgt.substring(0,hgt.length()-2));
                        if (height < 59 || height > 76) {
                            valid = false;
                            System.out.print("hgt param ");
                        }
                    } else if (format.equals("cm")) {
                        height = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                        if (height < 150 || height > 193) {
                            valid = false;
                            System.out.print("hgt param ");
                        }
                    } else {
                        valid = false;
                        System.out.print("hgt format("+format+") ");
                    }
                    break;
                case "hcl":
                    hcl = pairs[1];
                    if (hcl.charAt(0) != '#' || hcl.length() != 7) {
                        System.out.print("hcl format ");
                        valid = false;
                    }
                    //make sure hex number
                    try {
                        Long.parseLong(hcl.substring(1),16);
                    } catch (NumberFormatException e) {
                        valid = false;
                        System.out.print("hcl not hex ");
                    }
                    break;
                case "ecl":
                    ecl = pairs[1];
                    if (!ecl.equals("amb") &&
                        !ecl.equals("blu") &&
                        !ecl.equals("brn") &&
                        !ecl.equals("gry") &&
                        !ecl.equals("grn") &&
                        !ecl.equals("hzl") &&
                        !ecl.equals("oth") ) {
                        System.out.print("ecl invalid ");
                            valid = false;
                    }

                    break;
                default:
                    break;
            }
        }
        //if any essential fields invalid
        if (byr == 0 ||
                iyr == 0 ||
                eyr  == 0 ||
                pid == null ||
                hgt == null ||
                hcl == null ||
                ecl == null) {
            valid = false;
            System.out.print("missing info ");
        }

        System.out.println();
    }

    public boolean isValid() {
        return valid;
    }

}
