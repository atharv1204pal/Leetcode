class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length, n = classroom[0].length();
        List<Node> nodes = new ArrayList<>();
        int startIndex = -1;
        int litterCount = 0;

        // Collect special nodes
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startIndex = nodes.size();
                    nodes.add(new Node(i, j, false, false, -1));
                } else if (c == 'L') {
                    nodes.add(new Node(i, j, true, false, litterCount++));
                } else if (c == 'R') {
                    nodes.add(new Node(i, j, false, true, -1));
                }
            }
        }

        int totalNodes = nodes.size();
        int targetMask = (1 << litterCount) - 1;

        // Precompute distances between special nodes
        int[][] dist = new int[totalNodes][totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
            bfs(classroom, nodes.get(i), dist[i], nodes);
        }

        // DP table
        int[][][] dp = new int[1 << litterCount][totalNodes][energy + 1];
        for (int[][] arr2 : dp)
            for (int[] arr : arr2)
                Arrays.fill(arr, Integer.MAX_VALUE);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.moves));
        dp[0][startIndex][energy] = 0;
        pq.add(new State(startIndex, energy, 0, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            if (cur.mask == targetMask) return cur.moves;
            if (cur.moves > dp[cur.mask][cur.node][cur.energy]) continue;

            for (int j = 0; j < totalNodes; j++) {
                int d = dist[cur.node][j];
                if (d == Integer.MAX_VALUE || d > cur.energy) continue;

                int newEnergy = cur.energy - d;
                int newMask = cur.mask;
                Node next = nodes.get(j);

                if (next.isLitter) newMask |= (1 << next.litterIndex);
                if (next.isReset) newEnergy = energy;

                if (dp[newMask][j][newEnergy] > cur.moves + d) {
                    dp[newMask][j][newEnergy] = cur.moves + d;
                    pq.add(new State(j, newEnergy, newMask, cur.moves + d));
                }
            }
        }
        return -1;
    }

    // BFS from one special node to compute distances
    private void bfs(String[] classroom, Node start, int[] distRow, List<Node> nodes) {
        int m = classroom.length, n = classroom[0].length();
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start.x, start.y, 0});
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];

            // Update distances to special nodes
            for (int k = 0; k < distRow.length; k++) {
                Node node = nodes.get(k);
                if (node.x == x && node.y == y) {
                    distRow[k] = Math.min(distRow[k], d);
                }
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                if (visited[nx][ny]) continue;
                if (classroom[nx].charAt(ny) == 'X') continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, d + 1});
            }
        }
    }

    // Helper classes
    static class Node {
        int x, y;
        boolean isLitter, isReset;
        int litterIndex;
        Node(int x, int y, boolean isLitter, boolean isReset, int litterIndex) {
            this.x = x; this.y = y;
            this.isLitter = isLitter; this.isReset = isReset;
            this.litterIndex = litterIndex;
        }
    }

    static class State {
        int node, energy, mask, moves;
        State(int node, int energy, int mask, int moves) {
            this.node = node; this.energy = energy;
            this.mask = mask; this.moves = moves;
        }
    }
}
