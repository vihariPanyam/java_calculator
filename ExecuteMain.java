package com.calculator;

public class ExecuteMain {

    public static void main(String[] args) {

        try{
            String s = "23*33*65-87/98";
            String[] split = splitString(removeAllSpaceCharacters(s));

            String[] afterDivision = afterOperation(split, "/");
            for(String isDivisionComplete: afterDivision){
                boolean completed = false;
                if("/".equals(isDivisionComplete)){
                    afterDivision = afterOperation(split, "/");
                }
            }

            String[] afterMultiplication = afterOperation(afterDivision, "*");
            for(String isMultiplyComplete: afterMultiplication){
                boolean completed = false;
                if("*".equals(isMultiplyComplete)){
                    afterMultiplication = afterOperation(split, "*");
                }
            }

            String[] afterAddition = afterOperation(afterMultiplication, "+");
            for(String isAdditionComplete: afterAddition){
                boolean completed = false;
                if("+".equals(isAdditionComplete)){
                    afterAddition = afterOperation(split, "+");
                }
            }


            String[] afterSubstraction = afterOperation(afterAddition, "-");
            for(String isSubstractionComplete: afterSubstraction){
                boolean completed = false;
                if("-".equals(isSubstractionComplete)){
                    afterSubstraction = afterOperation(split, "-");
                }
            }

            for(String s1j: afterSubstraction){
                if(s1j != null)
                    System.out.println(s1j);
            }
        }

        catch(Exception e){
            System.out.println("Please Enter a valid value");
        }
    }

    public static String[] afterOperation(String[] split, String operation){

        String[] afterDivision = new String[100];
        int j = -1;
        int index = -1;
        boolean recurionflag = false;
        for(int i = 0 ; i < split.length; i++)
        {

            if(operation.equals(split[i]))
            {
                if("/".equals(operation)){
                    Double d1 = (toDouble(split[i-1])/toDouble(split[i+1]));
                    index = ++i;
                    afterDivision[j++] = String.valueOf(d1);
                    recurionflag = true;
                }else if("*".equals(operation)){
                    Double d1 = (toDouble(split[i-1]) * toDouble(split[i+1]));
                    index = ++i;
                    afterDivision[j++] = String.valueOf(d1);
                    recurionflag = true;

                }else if("+".equals(operation)){
                    Double d1 = (toDouble(split[i-1]) + toDouble(split[i+1]));
                    index = ++i;
                    afterDivision[j++] = String.valueOf(d1);
                    recurionflag = true;

                }else if("-".equals(operation)){
                    Double d1 = (toDouble(split[i-1]) - toDouble(split[i+1]));
                    index = ++i;
                    afterDivision[j++] = String.valueOf(d1);
                    recurionflag = true;

                }


            }else{
                afterDivision[++j] = split[i];
            }

            if (recurionflag) {
                for(int k = index; k< split.length; k++){

                    afterDivision[++j] =split[k];
                }
                return afterDivision;
            }
        }

        return afterDivision;
    }






    public static double toDouble(String s){
        return Double.parseDouble(s);
    }

    //Remove any extra spaces
    public static String removeAllSpaceCharacters(String st){
        return st.replaceAll("\\s+","");
    }

    /**
     * Split all the String based on special character
     * @param s
     * @return
     */
    public static String[] splitString(String s) throws NumberFormatException{

        String[] str = new String[10];
        int j = -1;
        int beginindex = 0;
        int endindex = 1;
        for (int i = 0; i < s.length(); i++)
        {
            if('+' == (s.charAt(i))
                    || '-'==(s.charAt(i))
                    || '*'==(s.charAt(i))
                    || '/' == (s.charAt(i)))
            {
                endindex = i;
                str[++j] = s.substring(beginindex, endindex);
                str[++j] = s.substring(endindex, endindex+1);
                beginindex = endindex+1;
            }
        }

        str[++j] =  s.substring(endindex+1);


        return str;
    }
}
