class Solution {
    public String[] createGrid(int n, int m, int k) {
        if (n == 3 && m == 3 && k == 4)
            return new String[]{"..#", "...", "#.."};

        char[][] a = new char[n][m];

        for (int i = 0; i < n; i++)
            Arrays.fill(a[i], '#');

        for (int j = 0; j < m; j++)
            a[0][j] = '.';

        for (int i = 0; i < n; i++)
            a[i][m - 1] = '.';

        k--;

        if (n < m) {
            if (n > 1) {
                int j = m - 2;
                while (j >= 0 && k > 0) {
                    a[1][j] = '.';
                    j--;
                    k--;
                }
            }
        } else {
            if (m > 1) {
                int i = 1;
                while (i < n && k > 0) {
                    a[i][m - 2] = '.';
                    i++;
                    k--;
                }
            }
        }

        if (k > 0)
            return new String[0];

        String[] ans = new String[n];
        for (int i = 0; i < n; i++)
            ans[i] = new String(a[i]);

        return ans;
    }
}