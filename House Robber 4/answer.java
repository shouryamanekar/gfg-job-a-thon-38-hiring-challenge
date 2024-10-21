class Solution {
    static final int MOD = 1000000007;

    public static long nCrModP(int n, int r) {
        if (r > n) return 0;
        long[] fact = new long[n + 1];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }
        return fact[n] * modInverse(fact[r], MOD) % MOD * modInverse(fact[n - r], MOD) % MOD;
    }

    public static long modPow(long x, long y, long p) {
        long res = 1;
        x = x % p;
        while (y > 0) {
            if ((y & 1) == 1) res = res * x % p;
            y = y >> 1;
            x = x * x % p;
        }
        return res;
    }

    public static long modInverse(long a, long p) {
        return modPow(a, p - 2, p);
    }

    public static int houseRobWays(int n, int k) {
        if (k == 0) return 1;
        if (k > (n + 1) / 2) return 0;
        if (k == n && n > 1) return 0;
        if (k == 1) return n;
        if (n == 1) return k == 1 ? 1 : 0;

        return (int) nCrModP(n - k + 1, k);
    }
}
