import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] nums = {2,3,1,1,4};
        int[][] intervals = {
                {1,3},
                {2,6},
                {10,12},
                {1,4},
                {4,7}
        };
        String s = " a ";
        int idx = s.lastIndexOf(' ');
        System.out.println(s.length()-1-idx);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1,o2)->o1[0]-o2[0]);
        int[][] merge = new int[0][0];
        List<int[]> ans = new ArrayList<>();
        for(int[] nums : intervals){
            if(ans.isEmpty()){
                ans.add(nums);
            }else{
                if(nums[0] > ans.get(ans.size()-1)[1]){
                    ans.add(nums);
                }else{
                    ans.get(ans.size()-1)[1] = nums[1] > ans.get(ans.size()-1)[1] ? nums[1] : ans.get(ans.size()-1)[1];
                }
            }
        }
        return  ans.toArray(merge);
    }




    public boolean canJump(int[] nums) {
        int len = nums.length;
        int start = 0;
        int end = 0;
        int maxEnd = 0;
        int i = 0;
        while(start <= i && i <= maxEnd){
            maxEnd = maxEnd > (nums[i] + i)? maxEnd: nums[i] + i;
            i++;
            if(maxEnd >= len-1) return true;
        }

        return false;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> out = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        while( m > 0){
            for(int i: matrix[0]){
                out.add(i);
            }
            for(int i = 1; i< m; i++){
                for(int j = 0; j < n; j++){
                    matrix[i-1][j] = matrix[i][j];
                }
            }
            m = m-1;
            int[][] temp = new int[n][m];
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    temp[n-1-j][i] =  matrix[i][j];
                }
            }
            matrix = temp;
            int t = m;
            m = n;
            n = t;
        }
        return out;
    }

    List<List<String>> out = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        boolean[] rows = new boolean[n];
        boolean[] cols = new boolean[n];
        boolean[] aixs = new boolean[4*n - 2];
        StringBuffer sb = new StringBuffer();
        List<String> path = new ArrayList<>();
        for(int i = 0; i < n; i++){
            sb.append('.');
        }
        for(int i = 0; i < n; i++){
            path.add(sb.toString());
        }
        backTrack(0, 0, rows, cols, aixs, path, n);
        return out;
    }

    private void backTrack(int i, int j, boolean[] rows, boolean[] cols, boolean[] aixs, List<String> path, int n){
        if( i == n) {
            out.add(new ArrayList(path));
            return;
        }
        for( ; j < n; j++){
            if(!rows[i] && !cols[j] && !aixs[i+j] && !aixs[i-j+3*n-2]){
                rows[i] = true;
                cols[j] = true;
                aixs[i+j] = true;
                aixs[i-j+3*n-2] = true;
                StringBuffer temp = new StringBuffer(path.get(i));
                path.remove(i);
                temp.replace(j,j+1, "Q");
                path.add(i, temp.toString());
                backTrack(i+1, 0, rows, cols, aixs, path, n);
                rows[i] = false;
                cols[j] = false;
                aixs[i+j] = false;
                aixs[i-j+3*n-2] = false;
                path.remove(i);
                temp.replace(j,j+1, ".");
                path.add(i, temp.toString());
            }
        }
    }

    public double myPow(double x, int n) {
        double t = x;
        long N = n;
        if(n == 0) return 1;
        if(n > 0){
            for(int i = 1; i < N; i++ ){
                x *= t;
            }
        }else{
            for(int i = 1; i < Math.abs(N); i++){
                x *= t;
            }
            x = 1/x;
        }
        return x;
    }

    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) return "0";
        int len1 = num1.length(), len2 = num2.length();
        int mjinwei = 0, myu = 0, sjinwei = 0, syu = 0;
        StringBuffer sb = new StringBuffer("0");
        for(int i = len1-1; i >= 0; i--){
            int n1 = Character.getNumericValue(num1.charAt(i));
            int idx = sb.length()-(len1-1-i)-1;
            for(int j = len2-1; j >= 0; j--){
                int n2 = Character.getNumericValue(num2.charAt(j));
                int mul = n1*n2+mjinwei;
                mjinwei = mul/10;
                myu = mul%10;
                if(idx >= 0){
                    int sum = Character.getNumericValue(sb.charAt(idx)) + myu + sjinwei;
                    sjinwei = sum/10;
                    syu = sum%10;
                    sb.deleteCharAt(idx);
                    sb.insert(idx, syu);
                    idx--;
                }else{
                    int sum = myu+sjinwei;
                    sjinwei = sum/10;
                    syu = sum%10;
                    sb.insert(0,syu);
                }
            }
            if(mjinwei > 0 || sjinwei > 0) sb.insert(0, mjinwei + sjinwei);
            mjinwei = 0;
            sjinwei = 0;
        }
        return sb.toString();
    }

//    List<List<Integer>> out = new ArrayList<>();
//    public List<List<Integer>> permute(int[] nums) {
//        int len = nums.length;
//        if(len == 0) return out;
//        Arrays.sort(nums);
//        boolean[] used = new boolean[len];
//        List<Integer> path = new ArrayList<>();
//        dfs(nums, len, 0, path, used);
//        return out;
//    }
//
//    private void dfs(int[] nums, int len, int depth, List<Integer> path, boolean[] used){
//        if(depth >= len ){
//            out.add(new ArrayList<>(path));
//            return;
//        }
//        for(int i = 0; i < len; i++){
//            if(!used[i]){
//                if(i > 0 && nums[i] == nums[i-1] && used[i-1] == false) continue;
//                path.add(nums[i]);
//                used[i] = true;
//                dfs(nums, len, depth+1, path, used);
//                path.remove(path.size()-1);
//                used[i] = false;
//            }
//        }
//    }

}
