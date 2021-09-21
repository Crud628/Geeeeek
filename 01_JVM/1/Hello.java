public class Hello{

	byte b = 12;
	short s = 199;
	int age = 23;
	long m = 111L;
	double d = 1.0d;
	float f = 3.3f;
	char c = 'a';
	boolean flag = false;
	
	/**
	 * 冒泡排序
	 */
	public static void main(String[] args) {
		int[] nums = {2,4,5,26,35,134,3,25,2,35};
		int n = nums.length;
		for (int i = 0;i < n;i++) {
			for (int j = 0;j < n;j++) {
				if (nums[i] < nums[j]) {
					int temp = nums[i];
					nums[i] = nums[j];
					nums[j] = temp;
				}
			}
		}
		for (int num: nums) {
			System.out.print(num+",");
		}
	}
}