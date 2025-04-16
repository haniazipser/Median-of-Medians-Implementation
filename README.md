# Median of Medians Algorithm

This algorithm implements the Median of Medians approach to efficiently find the k-th smallest element in an unsorted array in linear time.

## Description

### Divide into Groups of Five
The algorithm starts by dividing the current subarray into groups of five elements. These small groups are sorted using Insertion Sort, which performs efficiently for such small datasets (almost linear time).

### Select Group Medians
After sorting, the median of each group is found at the middle index (3rd element in a sorted group of 5). This median is then moved to the beginning of the array — specifically, for the k-th group, its median is placed at index `start + k`.

### Recursive Median of Medians
To find the "median of medians", we recursively apply the same selection process to the array of collected medians. This value will serve as our pivot, chosen deterministically to guarantee good performance.

### Three-Way Partitioning
Instead of the standard two-part partitioning, we divide the array into three parts: elements less than the pivot, equal to the pivot, and greater than the pivot.  
For this, we use the Dutch National Flag algorithm, a variation of the classic partitioning method optimized for handling duplicates.

### Recursive Selection
Once partitioned, we check where the k-th smallest element lies:
- If it's in the first group (less than pivot), we recurse into that group.
- If it's among the elements equal to the pivot, we return the pivot.
- Otherwise, we recurse into the group with elements greater than the pivot.

### Base Case (Small Arrays)
For very small arrays (size ≤ 2), we use Insertion Sort directly and return the k-th element.
