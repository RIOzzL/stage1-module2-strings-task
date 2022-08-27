package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MethodParser {
    private final String PRIVATE = "private";
    private final String PUBLIC = "public";
    private final String DEFAULT = "default";

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     * 1. access modifier - optional, followed by space: ' '
     * 2. return type - followed by space: ' '
     * 3. method name
     * 4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     * accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     * private void log(String value)
     * Vector3 distort(int x, int y, int z, float magnitude)
     * public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        try {
            checkString(signatureString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ArrayList<MethodSignature.Argument> arguments = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(signatureString, " (),");
        String accessModifierOrReturnType = st.nextToken();
        String accessModifier = setAccessModifier(accessModifierOrReturnType);
        String returnType;
        if (accessModifier == null) {
            returnType = accessModifierOrReturnType;
        } else {
            returnType = st.nextToken();
        }
        String methodName = st.nextToken();

        while (st.hasMoreTokens()) {
            MethodSignature.Argument argument = new MethodSignature.Argument(st.nextToken(), st.nextToken());
            arguments.add(argument);
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessModifier);
        methodSignature.setReturnType(returnType);
        // throw new UnsupportedOperationException("You should implement this method.");
        return methodSignature;
    }

    private String setAccessModifier(String accessModifier) {
        if (accessModifier.equals(PRIVATE) || accessModifier.equals(PUBLIC) || accessModifier.equals(DEFAULT)) {
            return accessModifier;
        } else {
            return null;
        }
    }

    private void checkString(String s) throws Exception {
        if (s.split(" ").length < 2) {
            throw new UnsupportedOperationException("Incorrect string signature.");
        } else {
            String[] splitForParameters = s.split("\\(|\\)");
            String[] parameters = splitForParameters[1].replaceAll(",", "").split(" ");
            // System.out.println(Arrays.toString(parameters));
            if (parameters.length % 2 != 0) {
                throw new UnsupportedOperationException("Incorrect string signature (incorrect parameters).");
            }
        }
    }
}
