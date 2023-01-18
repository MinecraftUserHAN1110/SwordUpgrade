package upgrade.sword.utils;

public class Data {
    private final String dataString;
    public Data(String dataString) {
        this.dataString = dataString;
    }

    public String getDataString() {
        return dataString;
    }

    public int length() {
        return dataString.length();
    }

    public int inject() {
        switch (length()) {
            case 1:
                return 1;
            case 2:
                return 10;
            case 3:
                return 100;
            case 4:
                return 1000;
            case 5:
                return 10000;
            default:
                break;
        }
        return 0;
    }

    public int getDamage() {
        int sum = 0;
        int statics = 1;
        for (Character c : dataString.toCharArray()) {
            String s = c.toString();

            if (inject() == 1) {
                if (s.contains("1")) {
                    sum += 1;
                } else if (s.contains("2")) {
                    sum += 2;
                } else if (s.contains("3")) {
                    sum += 3;
                } else if (s.contains("4")) {
                    sum += 4;
                } else if (s.contains("5")) {
                    sum += 5;
                } else if (s.contains("6")) {
                    sum += 6;
                } else if (s.contains("7")) {
                    sum += 7;
                } else if (s.contains("8")) {
                    sum += 8;
                } else if (s.contains("9")) {
                    sum += 9;
                } else if (s.contains("0")) {
                    sum += 0;
                }
                return sum;
            } else if (inject() == 10) {
                statics = 10;
                for (int i = 2; i > 0; i--) {
                    if (s.contains("1")) {
                        sum += 1 * statics;
                    } else if (s.contains("2")) {
                        sum += 2 * statics;
                    } else if (s.contains("3")) {
                        sum += 3 * statics;
                    } else if (s.contains("4")) {
                        sum += 4 * statics;
                    } else if (s.contains("5")) {
                        sum += 5 * statics;
                    } else if (s.contains("6")) {
                        sum += 6 * statics;
                    } else if (s.contains("7")) {
                        sum += 7 * statics;
                    } else if (s.contains("8")) {
                        sum += 8 * statics;
                    } else if (s.contains("9")) {
                        sum += 9 * statics;
                    } else if (s.contains("0")) {
                        sum += 0 * statics;
                    }

                    if (statics == 10) {
                        statics = 1;
                    } else if (statics == 1) {
                        break;
                    }
                }
            } else if (inject() == 100) {
                statics = 100;
                for (int i = 3; i > 0; i--) {
                    if (s.contains("1")) {
                        sum += 1 * statics;
                    } else if (s.contains("2")) {
                        sum += 2 * statics;
                    } else if (s.contains("3")) {
                        sum += 3 * statics;
                    } else if (s.contains("4")) {
                        sum += 4 * statics;
                    } else if (s.contains("5")) {
                        sum += 5 * statics;
                    } else if (s.contains("6")) {
                        sum += 6 * statics;
                    } else if (s.contains("7")) {
                        sum += 7 * statics;
                    } else if (s.contains("8")) {
                        sum += 8 * statics;
                    } else if (s.contains("9")) {
                        sum += 9 * statics;
                    } else if (s.contains("0")) {
                        sum += 0 * statics;
                    }
                    if (statics == 100) {
                        statics = 10;
                    } else if (statics == 10) {
                        statics = 1;
                    } else if (statics == 1) {
                        break;
                    }
                }
            }
        }
        return sum;
    }
}
