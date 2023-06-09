class FindSmallestNumberGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int r = letters.length - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (letters[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l == letters.length ? letters[0] : letters[l];
    }
}
