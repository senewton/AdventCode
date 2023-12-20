import java.util.Vector;

// Java program to find LCM of array without using GCD.
class LeastCommonMultiple {

    // Returns LCM of arr[0..n-1]
    static long LCM(int arr[], int n) {
        // Find the maximum value in arr[]
        int max_num = 0;
        for (int i = 0; i < n; i++) {
            if (max_num < arr[i]) {
                max_num = arr[i];
            }
        }

        // Initialize result
        long res = 1;

        // Find all factors that are present in
        // two or more array elements.
        int x = 2; // Current factor.
        while (x <= max_num) {
            // To store indexes of all array
            // elements that are divisible by x.
            Vector<Integer> indexes = new Vector<>();
            for (int j = 0; j < n; j++) {
                if (arr[j] % x == 0) {
                    indexes.add(indexes.size(), j);
                }
            }

            // If there are 2 or more array elements
            // that are divisible by x.
            if (indexes.size() >= 2) {
                // Reduce all array elements divisible
                // by x.
                for (int j = 0; j < indexes.size(); j++) {
                    arr[indexes.get(j)] = arr[indexes.get(j)] / x;
                }

                res = res * x;
            } else {
                x++;
            }
        }

        // Then multiply all reduced array elements
        for (int i = 0; i < n; i++) {
            res = res * arr[i];
        }

        return res;
    }
}
