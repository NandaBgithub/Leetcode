import java.util.List;
import java.util.ArrayList;

class Solution {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        int[] count = new int[n+1];

        for(int element: nums){
            if (res.size() < ++count[element]){
                res.add(new ArrayList<>());
            }
            res.get(count[element]-1).add(element);
        }

        return res;
    }

}