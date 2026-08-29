class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] result = new int[n];

        // Pair values with indices
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i]; // value
            arr[i][1] = i;       // index
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // Create UnionFind object 
        UnionFind uf = new UnionFind(n);

        // Union adjacent values if difference <= limit
        for (int i = 1; i < n; i++) {
            if (arr[i][0] - arr[i - 1][0] <= limit) {
                uf.union(arr[i][1], arr[i - 1][1]);
            }
        }

        // Collecting groups
        Map<Integer, List<Integer>> groupIndices = new HashMap<>();
        Map<Integer, List<Integer>> groupValues = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = uf.find(i); 
            groupIndices.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
            groupValues.computeIfAbsent(root, k -> new ArrayList<>()).add(nums[i]);
        }

        // Sorting values inside each group and place them back
        for (int root : groupIndices.keySet()) {
            List<Integer> indices = groupIndices.get(root);
            List<Integer> values = groupValues.get(root);

            Collections.sort(indices);
            Collections.sort(values);

            for (int i = 0; i < indices.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }

        // Counting swaps (difference from original)
        int swapCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != result[i]) swapCount++;
        }
        //System.out.println("Total swaps performed: " + swapCount);

        return result;
    }

    // Union-Find helper class
    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }
        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int x, int y) {
            int rx = find(x), ry = find(y);
            if (rx == ry) return;
            if (rank[rx] < rank[ry]) parent[rx] = ry;
            else if (rank[rx] > rank[ry]) parent[ry] = rx;
            else {
                parent[ry] = rx;
                rank[rx]++;
            }
        }
    }
}
