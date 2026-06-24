class Solution {
    public String reverseVowels(String s) 
    {
        String Res = "";
        char [] c = s.toCharArray();
        int i = 0 ,j=c.length-1;
        while(i<j)
        {
            while(i<j && !isVowel(c[i]))
            {
                i++;
            }
            while(i<j && !isVowel(c[j]))
            {
                j--;
            }
            char temp = c[i];
            c[i]=c[j];
            c[j]=temp;
            i++;
            j--;
        }
        return new String(c);  
    }
     public static boolean isVowel(char ch)
    {
        ch = Character.toLowerCase(ch);
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' ; 
    }
}