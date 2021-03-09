package helpers;

import static java.lang.Integer.parseInt;

public class Environment {
                public static final int failTest = parseInt(System.getProperty("test_threshold", "-1"));
}


