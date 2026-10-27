class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;           // Length of Array 
        int[] arr = new int[n-k+1];    // Lenth of the new array
        int ri = 0;
        // Storing the index of elements
        Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < nums.length; i++) {
			// remove numbers out of range k
			while (!q.isEmpty() && q.peek() < i - k + 1) {
				q.poll();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) {
				q.pollLast();
			}
			// q contains index... arr contains content
			q.offer(i);
			if (i >= k - 1) {
				arr[ri++] = nums[q.peek()];
			}
		}
		return arr;
    }
}