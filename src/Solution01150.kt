class Solution01150 {
    fun isMajorityElement2(nums: IntArray, target: Int): Boolean {
        var count = 0
        for (n in nums) {
            if (n == target) count++
            else if (n > target) break
        }
        return (count > nums.size / 2)
    }

    fun isMajorityElement(nums: IntArray, target: Int): Boolean {
        return nums.indices.filter { nums[it] == target }.size > nums.size / 2
    }
}