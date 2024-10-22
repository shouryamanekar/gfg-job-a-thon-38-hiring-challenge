class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[2];
    }

    private static void insert(TrieNode root, int num) {
        TrieNode node = root;
        for(int i = 31; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(node.children[bit] == null){
                node.children[bit] = new TrieNode();
            }
            node = node.children[bit];
        }
    }

    private static int query(TrieNode root, int num) {
        TrieNode node = root;
        int maxXor = 0;
        for(int i = 31; i >= 0; i--){
            if(node == null) break;
            int bit = (num >> i) & 1;
            
            if(node.children[1 - bit] != null){
                maxXor |= (1 << i);
                node = node.children[1 - bit];
            }
            else{
                node = node.children[bit];
            }
        }
        return maxXor;
    }

    public static int maximumSumOfXorSums(int n, int[] arr) {

        int[] prefixXor = new int[n];
        prefixXor[0] = arr[0];
        for(int i = 1; i < n; i++) {
            prefixXor[i] = prefixXor[i-1] ^ arr[i];
        }

        int[] dp1 = new int[n];
        TrieNode root1 = new TrieNode();
        insert(root1, 0); 
        dp1[0] = arr[0];
        insert(root1, prefixXor[0]);
        for(int i = 1; i < n; i++) {
            dp1[i] = Math.max(dp1[i-1], query(root1, prefixXor[i]));
            insert(root1, prefixXor[i]);
        }

        int[] dp2 = new int[n];
        int[] suffixXor = new int[n];
        suffixXor[n-1] = arr[n-1];
        for(int i = n-2; i >=0; i--){
            suffixXor[i] = suffixXor[i+1] ^ arr[i];
        }

        int[] reverseSuffixXor = new int[n];
        for(int i = 0; i < n; i++) {
            reverseSuffixXor[i] = suffixXor[i];
        }

        TrieNode root2 = new TrieNode();
        insert(root2, 0);
        dp2[n-1] = arr[n-1];
        insert(root2, suffixXor[n-1]);
        for(int i = n-2; i >=0; i--){
            dp2[i] = Math.max(dp2[i+1], query(root2, suffixXor[i]));
            insert(root2, suffixXor[i]);
        }

        int maxSum = 0;
        for(int i = 0; i < n-1; i++) {
            maxSum = Math.max(maxSum, dp1[i] + dp2[i+1]);
        }

        return maxSum;
    }
}