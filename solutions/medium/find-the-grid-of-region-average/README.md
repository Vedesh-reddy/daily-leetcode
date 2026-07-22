# Find the Grid of Region Average (Medium)

https://leetcode.com/problems/find-the-grid-of-region-average/

For every possible 3x3 top-left corner, check whether all adjacent pixel pairs within that subgrid differ by at most threshold; if so it's a valid region, so compute its floor average and add that value into accumulator arrays (sum and count) for each of the 9 cells it covers. After scanning all subgrids, each cell's result is the floor of its accumulated sum divided by count, or the original pixel value if it belongs to no region. This brute-force scan checks O(m*n) subgrid origins, each requiring O(1) work (9 cells, fixed adjacency checks), giving overall O(m*n) time and O(m*n) space for the auxiliary arrays.
