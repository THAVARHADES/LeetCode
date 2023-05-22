import java.util.*;

class Solution {
    class Point {
        public int x, y;

        public Point(int a, int b) {
            this.x = a;
            this.y = b;
        }
    }

    final int[] dx = new int[] { 0, 0, 1, -1 };
    final int[] dy = new int[] { 1, -1, 0, 0 };

    public int shortestBridge(int[][] A) {
        int dist = 0;

        if (A != null && A.length != 0) {
            int R = A.length, C = A[0].length;
            int i, j, x, y, newX, newY;
            boolean found = false;

            Queue<Point> queue = new LinkedList<Point>();
            for (i = 0; i < R && !found; i++) {
                for (j = 0; j < C && !found; j++) {
                    if (A[i][j] == 1) {
                        DFS(A, i, j, R, C, queue);
                        found = true;
                    }
                }
            }

            dist = 0;
            while (!queue.isEmpty()) {
                int N = queue.size();
                for (i = 0; i < N; i++) {
                    x = queue.peek().x;
                    y = queue.peek().y;
                    queue.poll();

                    if (A[x][y] != -2) {
                        // mark as visited by setting the value as -2
                        A[x][y] = -2;

                        for (j = 0; j < 4; j++) {
                            newX = x + dx[j];
                            newY = y + dy[j];
                            if (newX >= 0 && newX < R && newY >= 0 && newY < C) {
                                if (A[newX][newY] == 1) // second island's vertex detected
                                    return dist;
                                else if (A[newX][newY] == 0) // "water" detected - so keep "swimming" ahead
                                    queue.offer(new Point(newX, newY));
                            }
                        }
                    }
                }

                ++dist;
            }
        }

        return dist;
    }

    // identify one island
    private void DFS(int[][] A, int i, int j, int R, int C, Queue<Point> queue) {
        if (i >= 0 && i < R && j >= 0 && j < C && A[i][j] == 1) {
            A[i][j] = -1;

            // only the "boundary" of the island is to be considered for searching second
            // island
            if (isBorder(A, i, j, R, C))
                queue.offer(new Point(i, j));

            DFS(A, i + 1, j, R, C, queue);
            DFS(A, i - 1, j, R, C, queue);
            DFS(A, i, j + 1, R, C, queue);
            DFS(A, i, j - 1, R, C, queue);
        }
    }

    private boolean isBorder(int[][] A, int i, int j, int R, int C) {
        if (i < R - 1 && A[i + 1][j] == 0)
            return true;

        if (i > 0 && A[i - 1][j] == 0)
            return true;

        if (j < C - 1 && A[i][j + 1] == 0)
            return true;

        if (j > 0 && A[i][j - 1] == 0)
            return true;

        return false;
    }
}