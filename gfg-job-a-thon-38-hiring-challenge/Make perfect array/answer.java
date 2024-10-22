class Solution {
    public static int makePerfect(int n, int[] arr) {
        // code here
        if(arr == null || n==0 || n==1)
        {
            return 0;
        }
        
        int operations=0;
        
        for (int i=1; i < n; i++) {
            if (arr[i] == arr[i-1]) {
                operations++; 
                int newValue = getNewValue(arr[i-1], i + 1 < n ?arr[i + 1]: -1);
                arr[i] = newValue;
            }
        }
        return operations;
    }

    private static int getNewValue(int prev, int next) {
        int newValue = 1;

        while (newValue == prev || newValue == next) { 
            newValue++; 
            
        } 
        return newValue; 
    }
    }