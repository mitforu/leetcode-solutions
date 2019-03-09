import java.util.*;

public class Main {
    static int sum = 0;

    public static void main(String[] args) {


        int[][] test = {{3, 0, 8, 4}, {2, 4, 5, 7},{9, 2, 6, 3}, {0, 3, 1, 0}};

        maxIncreaseKeepingSkyline(test);

    }


    public static int maxIncreaseKeepingSkyline(int[][] grid) {


        int highestValuesInEachRow[] = new int[grid.length];
        int highestValuesInEachColumn[] = new int[grid[0].length];

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                if (highestValuesInEachRow[i] < grid[i][j]) {
                    highestValuesInEachRow[i] = grid[i][j];
                }

                if(highestValuesInEachColumn[j] < grid[i][j]){
                    highestValuesInEachColumn[j] = grid[i][j];
                }
            }
        }

        int finalAmount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (highestValuesInEachRow[i] < highestValuesInEachColumn[j]){
                    finalAmount = finalAmount + (highestValuesInEachRow[i] - grid[i][j]);
                }else{
                    finalAmount = finalAmount + (highestValuesInEachColumn[j] - grid[i][j]);
                }
            }
        }

        return finalAmount;
    }


    public static String reverseWords1(String s) {

        char[] c = s.trim().toCharArray();
        char[] newString = new char[c.length];

        for (int j = c.length; j >= 0 ; j--){

            while (c[j] == ' '){
                j--;
            }





        }


        return s;
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int count = 0;

        if (wordList.contains(endWord)) {
            for (String word : wordList) {
                char[] begin = beginWord.toCharArray();
                char[] end = word.toCharArray();

                if (word.equals(endWord)) {
                    count++;
                    beginWord = word;
                }

                int differentCount = 0;
                boolean shouldSkipCurrentWord = false;

                for (int i = 0; i < begin.length; i++) {
                    if (begin[i] != end[i]) {
                        differentCount++;
                    }
                    if (differentCount >= 2) {
                        shouldSkipCurrentWord = true;
                        break;
                    }
                }

                if (!shouldSkipCurrentWord && !word.equals(beginWord)) {
                    beginWord = word;
                    count++;
                }
            }
        } else {
            return 0;
        }

        if (count == 1) return 0;
        if (count > 0) return count;

        return count;
    }


    public static int minAddToMakeValid(String S) {

        Stack<Character> stack = new Stack<>();
        for (Character c : S.toCharArray()) {

            if (stack.size() > 0) {
                Character c1 = stack.peek();
                if (c == ')' && c1 == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        int patternLength = pattern.length();
        char[] patternChar = pattern.toCharArray();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            if (word.length() == patternLength) {

                Map<Character, Character> map = new HashMap<>();
                char[] w = word.toCharArray();
                boolean value = true;

                for (int j = 0; j < patternLength; j++) {
                    char key = patternChar[j];
                    Character character = map.get(key);
                    char c1 = w[j];
                    if (character == null) {
                        for (Character c : map.values()) {
                            if (c == c1) {
                                value = false;
                                break;
                            }
                        }
                        map.put(key, c1);
                    } else {
                        if (character != c1) {
                            value = false;
                            break;
                        }
                    }
                }

                if (value) {
                    result.add(word);
                }
            }
        }


        List<String> res = new LinkedList<>();
        for (String w : words) {
            int[] p = new int[26], s = new int[26];
            boolean same = true;
            for (int i = 0; i < w.length(); i++) {
                if (s[w.charAt(i) - 'a'] != p[pattern.charAt(i) - 'a']) {
                    same = false;
                    break;
                } else {
                    s[w.charAt(i) - 'a'] = p[pattern.charAt(i) - 'a'] = i + 1;
                }
            }
            if (same) res.add(w);
        }
        return res;


    }

    public static TreeNode insertIntoBST(TreeNode root, int val) {

        System.out.println(root.val);

        if (root.val < val) {
            if (root.right != null) {
                insertIntoBST(root.right, val);
            } else {
                root.right = new TreeNode(val);
            }

        }

        if (root.val > val) {
            if (root.left != null) {
                insertIntoBST(root.left, val);
            } else {
                root.left = new TreeNode(val);
            }
        }

        return root;
    }

    public static int rangeSumBST(TreeNode root, int L, int R) {
        int[] a = new int[1];
        calculate(root, L, R, a);
        return a[0];
    }

    public static void calculate(TreeNode root, int L, int R, int[] a) {
        if (root.val >= L && root.val <= R) {
            a[0] = a[0] + root.val;
        }
        if (root.left != null) calculate(root.left, L, R, a);
        if (root.right != null) calculate(root.right, L, R, a);
        System.out.println(root.val);
    }


    public static int hammingDistance1(int x, int y) {
        int count = 0;

        int a = x ^ y;
        char c[] = Integer.toBinaryString(a).toCharArray();

        for (char s : c) {
            if (s == '1') count++;
        }
        return count;
    }

    public static int[][] kClosest(int[][] points, int K) {
        Map<Double, int[]> test = new HashMap<>();

        double[] result = new double[points.length];

        int[][] actualResult = new int[K][2];

        for (int i = 0; i < points.length; i++) {
            int[] a = points[i];
            int x = a[0] < 0 ? -a[0] : a[0];
            int y = a[1] < 0 ? -a[1] : a[1];
            double sqrt = Math.sqrt(x * x + y * y);
            test.put(sqrt, a);
            result[i] = sqrt;
        }

        Arrays.sort(result);

        for (int i = 0; i < K; i++) {
            actualResult[i] = test.get(result[i]);
        }

        return actualResult;
    }

    public int findComplement(int num) {


        return num;
    }


    public static int[] numberOfLines(int[] widths, String S) {
        int[] result = new int[2];
        char[] charArray = S.toCharArray();

        int total = 0;
        for (int i = 0; i < charArray.length; i++) {

            total = total + widths[(int) charArray[i] - 97];

            if (i == 0) {
                result[0] = 1;
            }
            result[1] = total;

            if (total == 100) {
                total = 0;
                result[0]++;
            } else if (total > 100) {
                total = 0;
                i--;
                result[0]++;
            }
        }
        return result;
    }

    public static int[] shortestToChar(String S, char C) {
        int length = S.length();
        int[] result = new int[length];

        int lastIndexOfC = -1;
        int count = 0;

        while (count < length - 1) {
            if (lastIndexOfC == -1) {
                int indexOfC = S.indexOf(C);
                count = indexOfC;
                int x = indexOfC;
                for (int i = 0; i < indexOfC; i++) {
                    result[i] = x;
                    x--;
                }
            } else {
                int indexOfC = S.indexOf(C, count + 1);
                if (indexOfC == -1) {
                    int x = 0;
                    for (int i = lastIndexOfC; i < S.length(); i++) {
                        result[i] = x;
                        x++;
                    }
                    indexOfC = length;
                } else {
                    int idx = indexOfC;
                    int lstIdx = lastIndexOfC;
                    int i = 1;
                    int j = 1;
                    while (idx != lstIdx && idx - lstIdx > 1) {
                        result[lstIdx + 1] = i;
                        result[idx - 1] = j;
                        i++;
                        j++;
                        lstIdx++;
                        idx--;
                    }
                }
                count = indexOfC;
            }

            lastIndexOfC = count;
        }

        return result;
    }

    public static int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    public static boolean isUnivalTree(TreeNode root) {
//        List<Integer> s = new LinkedList<>();
//        universal(root, s);
//
//        System.out.println(s);
//        for (int i = 0; i < s.size(); i++) {
//            if(s.get(0) != s.get(i)) return false;
//        }
//
//        return true;
        return universal1(root, true);
    }

    public static boolean universal1(TreeNode root, boolean s) {
        boolean x = true;
        if (root.right != null) {
            x = (root.val == root.right.val) && s;
            universal1(root.right, x);
        }
        if (root.left != null) {
            x = (root.val == root.left.val) && s;
            universal1(root.left, x);
        }
        return x;
    }

    public static void universal(TreeNode root, List<Integer> s) {
        if (root.right != null) {
            universal(root.right, s);
        }
        if (root.left != null) {
            universal(root.left, s);
        }
        s.add(root.val);
    }


    public static String reverseWords(String s) {

        char[] c = s.toCharArray();
        char[] result = new char[s.length()];
        int indexOfSpaceTop = 0;
        int index = 0;
        do {
            int count = 0;
            int indexOfSpace = s.indexOf(" ", indexOfSpaceTop + 1);
            indexOfSpaceTop = indexOfSpace;
            if (indexOfSpace == -1) {
                count = s.length();
                indexOfSpace = s.length();
            } else {
                count = indexOfSpace;
            }
            for (int i = index; i < indexOfSpace; i++) {
                result[count - 1] = c[i];
                count--;
                index++;
            }
            if (indexOfSpaceTop != -1) {
                result[indexOfSpace] = ' ';
                index++;
            }
        } while (indexOfSpaceTop != -1);

        return String.valueOf(result);
    }


    public static List<String> subdomainVisits(String[] cpdomains) {

        Map<String, Integer> result = new HashMap<>();

        for (String s : cpdomains) {
            String[] v = s.split(" ");
            Integer count = new Integer(v[0]);

            String actualString = v[1];

            Integer totalCount = result.get(actualString);
            if (totalCount == null) {
                result.put(actualString, count);
            } else {
                result.put(actualString, count + totalCount);
            }

            StringBuffer sb = new StringBuffer();
            String[] s1 = actualString.split("\\.");
            int length = s1.length;
            if (length == 3) {
                String key = sb.append(s1[1]).append(".").append(s1[2]).toString();
                Integer val = result.get(key);
                if (val == null) {
                    result.put(key, count);
                } else {
                    result.put(key, count + val);
                }

                String key1 = s1[2];
                Integer val1 = result.get(key1);
                if (val1 == null) {
                    result.put(key1, count);
                } else {
                    result.put(key1, count + val1);
                }
            }

            if (length == 2) {
                String key = s1[1];
                Integer val = result.get(key);
                if (val == null) {
                    result.put(key, count);
                } else {
                    result.put(key, count + val);
                }
            }
        }


//        for (String s: cpdomains){
//
//            String[] v = s.split(" ");
//            Integer count  = new Integer(v[0]);
//            if(count == null){
//                count = 0;
//            }
//
//            Integer resultCount = result.get(v[1]);
//            if(resultCount == null){
//                result.put(v[1], count);
//            }else if(resultCount != null){
//                result.put(v[1], count + resultCount);
//            }
//
//            String first = v[1].substring(v[1].indexOf(".")+1);
//            Integer firstIndex = result.get(first);
//            if(firstIndex == null){
//                result.put(first, count);
//                String second = first.substring(first.indexOf(".")+1);
//                Integer secondIndex = result.get(second);
//                if(secondIndex == null){
//                    result.put(second, count);
//                }else if(secondIndex != null){
//                    result.put(second, secondIndex + count);
//                }
//            }else if(firstIndex != null){
//                result.put(first, count + firstIndex);
//                if(first.indexOf(".") > 0){
//                    String second = first.substring(first.indexOf(".")+1);
//                    Integer secondIndex = result.get(second);
//                    if(secondIndex == null){
//                        result.put(second, count);
//                    }else if(secondIndex != null){
//                        result.put(second, secondIndex + count);
//                    }
//                }
//            }
//        }

        List<String> s = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            StringBuffer rb1 = new StringBuffer();
            rb1.append(entry.getValue());
            rb1.append(' ');
            rb1.append(entry.getKey());
            s.add(rb1.toString());
        }

        return s;
    }


    public static ListNode middleNode(ListNode head) {
        if (head == null) return null;
        int length = 0;
        ListNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
            length++;
        }

        int toFind = length / 2 + length % 2;

        for (int i = 0; i < toFind; i++) {
            head = head.next;
        }
        return head;
    }

    public static int maxDepth(Node root) {

        if (root == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node current = queue.poll();
                if (current.children != null) {
                    for (Node child : current.children) queue.offer(child);
                }
            }

            depth++;
        }

        return depth;

//        if (root == null) return 0;
//        if (root.children == null || root.children.isEmpty()) return 1;
//
//        List<Integer> heights = new LinkedList<>();
//        for (Node n : root.children) {
//            heights.add(maxDepth(n));
//        }
//        return Collections.max(heights) + 1;
    }


    public static int[][] transpose(int[][] A) {

        int length = A[0].length;
        int length1 = A.length;

        int[][] B = new int[length][length1];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length1; j++) {
                B[i][j] = A[j][i];
            }
        }

        return B;
    }


    public static TreeNode searchBST(TreeNode root, int val) {
        TreeNode currentNode = root;
        while (currentNode.val != val) {
            if (currentNode == null) return null;
            if (val < currentNode.val) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return currentNode;
    }

    public static List<Integer> preorder(Node root) {

        List<Integer> result = new ArrayList<>();
        preorder1(root, result);
        return result;
    }

    public static List<Integer> preorder1(Node root, List<Integer> result) {
        if (root == null) {
            return result;
        }
        result.add(root.val);
        if (root.children != null) {
            for (Node n : root.children) {
                preorder1(n, result);
            }
        }
        return result;
    }

    public static List<Integer> postorder(Node root) {
        List<Integer> result = new ArrayList<>();
        postorder1(root, result);
        return result;
    }

    public static List<Integer> postorder1(Node root, List<Integer> result) {
        if (root == null) {
            return result;
        }
        if (root.children != null) {
            for (Node n : root.children) {
                postorder1(n, result);
            }
        }
        result.add(root.val);
        return result;
    }

    public int getValue(Node root) {
        return root.val;
    }


    public static int[] sortArrayByParityII(int[] A) {

        int length = A.length;

        Stack<Integer> oddNumber = new Stack<>();
        Stack<Integer> evenNumber = new Stack<>();

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0 && A[i] % 2 == 1) {
                if (evenNumber.size() > 0) {
                    int location = evenNumber.pop();
                    int temp = A[location];
                    A[location] = A[i];
                    A[i] = temp;
                } else {
                    oddNumber.push(i);
                }
            }

            if (i % 2 == 1 && A[i] % 2 == 0) {
                if (oddNumber.size() > 0) {
                    int location = oddNumber.pop();
                    int temp = A[location];
                    A[location] = A[i];
                    A[i] = temp;
                } else {
                    evenNumber.push(i);
                }
            }
        }
        return A;
    }


    public static List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> result = new ArrayList<>();

        for (int i = left; i <= right; i++) {

            int val = i;
            boolean isSelfDividing = true;

            while (val > 0) {
                System.out.println("Processing " + val);
                int test = val % 10;
                if (test == 0) {
                    isSelfDividing = false;
                    break;
                }
                if (i % test != 0) {
                    isSelfDividing = false;
                }
                val = val / 10;
            }
            if (isSelfDividing) {
                result.add(i);
            }
        }
        return result;
    }

    public static int repeatedNTimes(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();

        int valueToLookFor = A.length / 2;

        for (int a : A) {
            Integer integer = map.get(a);
            if (integer == null) {
                map.put(a, 1);
            } else {
                int value = integer + 1;
                if (value == valueToLookFor) {
                    return a;
                }
                map.put(a, value);
            }
        }
        return 0;
    }

    public static int peakIndexInMountainArray(int[] A) {

        int length = A.length;

        for (int i = 1; i <= length - 2; i++) {
            if (A[i - 1] < A[i] && A[i] > A[i + 1]) {
                return i;
            }
        }
        return 0;
    }

    public static int[] diStringMatch(String S) {

        int length = S.length();
        char c[] = S.toCharArray();
        int a[] = new int[length + 1];

        int high = length;
        int low = 0;

        for (int i = 0; i < length; i++) {
            if (c[i] == 'I') {
                a[i] = low++;
            }
            if (c[i] == 'D') {
                a[i] = high--;
            }
        }
        a[length] = low;
        return a;
    }

    public static int hammingDistance(int x, int y) {
        int n = x ^ y;

        int count = 0;
        while (n != 0) {
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    public static boolean judgeCircle(String moves) {
        long sum = 0;
        char c[] = moves.toCharArray();
        for (char x : c) {
            if (x == 'R') {
                sum = sum + 120;
                continue;
            } else if (x == 'L') {
                sum = sum + (-120);
                continue;
            } else if (x == 'U') {
                sum = sum + (50);
                continue;
            } else if (x == 'D') {
                sum = sum + (-50);
                continue;
            }
        }
        return sum == 0;
    }

    public static int[][] flipAndInvertImage(int[][] A) {

        for (int[] a : A) {
            if (a.length == 1) {
                a[0] = a[0] ^ 1;
                continue;
            }

            boolean isEven = a.length % 2 == 0;

            int length = a.length - 1;
            int size = a.length - 1;
            for (int i = 0; i < size; i++) {
                if (!isEven && i == (size + 1) / 2) {
                    a[i] = a[i] ^ 1;
                    break;
                }
                int temp = a[i];
                a[i] = a[length] ^ 1;
                a[length] = temp ^ 1;
                length--;
                if (isEven && i + 1 == (size + 1) / 2) {
                    break;
                }
            }
        }
        return A;
    }

    public static int[] sortArrayByParity(int[] A) {

        int lastIndexOfEventItemPut = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                int temp = A[lastIndexOfEventItemPut];
                A[lastIndexOfEventItemPut] = A[i];
                A[i] = temp;
                ++lastIndexOfEventItemPut;
            }
        }
        return A;
    }

    public static int uniqueMorseRepresentations(String[] words) {
        String[] alpha = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."
        };

        Set<String> uniqueValue = new HashSet<>();

        for (String word : words) {
            char[] charArray = word.toCharArray();

            StringBuffer sb = new StringBuffer();

            for (char c : charArray) {
                sb.append(alpha[25 - (122 - (int) c)]);
            }
            uniqueValue.add(sb.toString());
        }

        return uniqueValue.size();
    }

    public static String toLowerCase(String str) {

        char[] charArray = str.toCharArray();

        int length = charArray.length;

        for (int i = 0; i < length; i++) {
            int charValue = (int) charArray[i];
            if (charValue >= 65 && charValue <= 90) {
                charValue = charValue + 32;
            }
            charArray[i] = (char) charValue;
        }

        return new String(charArray);
    }


    public static int numUniqueEmails(String[] emails) {

        Set<String> mail = new HashSet<>();


        for (String email : emails) {


            StringBuffer sb = new StringBuffer();

            char[] m = email.toCharArray();

            boolean skipStarts = false;
            boolean domain = false;

            for (char c : m) {

                if (('.' != c && '+' != c && !skipStarts) || domain) {
                    sb.append(c);
                }

                if ('+' == c) {
                    skipStarts = true;
                }
                if ('@' == c) {
                    sb.append(c);
                    domain = true;
                    skipStarts = false;
                }
            }
            mail.add(sb.toString());
        }

        return mail.size();
    }

}
