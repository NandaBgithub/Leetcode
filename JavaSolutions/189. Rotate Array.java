import java.util.Arrays;

class Solution {
    public void rotate(int[] nums, int k) {
        int[] newnums = roundrobin(nums, k);
        System.out.println(Arrays.toString(newnums));
        System.arraycopy(newnums, 0, nums, 0, newnums.length);
    }


    private int[] roundrobin(int[] nums, int k){
        int remainder;
        int n = nums.length;
        
        if (k > n){
            remainder = k % n;

            while (remainder > n){
                remainder = remainder % n;
            }
        } else {
            remainder = k;
        }

        int[] tail = Arrays.copyOfRange(nums, n-remainder, n);
        int[] head = Arrays.copyOfRange(nums, 0, n-remainder);
        int[] result = concatenateArrays(tail, head);
        return result;
    }

    private int[] concatenateArrays(int[] head, int[] tail){
        int h = head.length;
        int t = tail.length;

        int[] result = new int[h + t];
        System.arraycopy(head, 0, result, 0, h);
        System.arraycopy(tail, 0, result, h, t);
        return result;
    }
}