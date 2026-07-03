class Solution 
{
    public int compress(char[] chars) 
    {
        int n = chars.length , i = 0;

        int index = 0;

        while (i < n)
        {
            char ch = chars[i];
            int count = 0;

            while (i < n && chars[i] == ch) 
            {
                i++;
                count++;
            }

            chars[index] = ch; // chars[index++] =ch;
            index++;

            if (count > 1) 
            {
                String countStr = Integer.toString(count);

                for (char c : countStr.toCharArray()) 
                {
                    chars[index] = c;
                    index++;
                }
            }
        }
        return index;
    }
}