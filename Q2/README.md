## Problem description
You are helping a local bakery manage their order numbers. Each order has a numeric code,
but the bakery wants to know which combination of the digits would give the largest
possible order code and the smallest possible order code for record-keeping and sorting
purposes. To assist, you decide to write a Java program that takes an order code from the
user, filters out any non-digit characters, rearranges the digits to find the largest possible
number, and also rearranges them to find the smallest possible number. The program then
displays both results so the bakery can quickly organize their orders.
You must implement and use the following methods:
1. String getLargestNum(String num)
2. String getSmallestNum(String num)
   *You may create and use any additional helper methods if necessary.
   Input:
   The first line contains a string num and may contain digits and non-digit characters (which
   should be ignored).
   Output:
   Print the largest and smallest possible number formed by rearranging the digits.
---
## Solution Explanation
**String getLargestNum(String num)**
#### 1. Count total digits
- Initializes `count = 0`
- If the character is between '0' and '9', `count++`
#### 2. Extract digits only
- Create `char[] digits` of `size count`
- A `for` loop is used to extract digits and store it into digits[] array
- `Non-digit` characters are `skipped`
#### 3. Sort digits in descending order
- Using `nested loop`, if `digits[j] < digits[j+1]`, swap them
- Get `largest` number
#### 4. Convert sorted array back to string
- Create an empty string `sum = ""`
- Using `for` loop to add all the elements in the digits[] to sum

**String getSmallestNum(String num)**
#### 1. Start from the largest number
- Call `getLargestNum(num)` to reuse the sorted descending digits
- Convert the result to `char[] digits`
#### 2. Reverse the array
- Swap the `first and last` digits, `second and second-last digits`, etc using `for` loop
- Digits are sorted in `ascending` order
#### 3. Convert sorted array back to string
- Create an empty string `sum = ""`
- Loop through digits[] and add it to sum only if the `digit is not equal to '0'`

**Main Method**
#### 1. Prompt user to enter a string(num)
#### 2. Print largest and smallest number
- Largest number = `getLargestNum(num)`
- Smallest number = `getSmallestNum(num)`
---
## Sample input and output
Sample 1

![sample_output_1.png](sample%20cases/sample_output_1.png)

Sample 2

![sample_output_2.png](sample%20cases/sample_output_2.png)

Sample 3

![sample_output_3.png](sample%20cases/sample_output_3.png)

## Source code
See [Q2.java](Q2.java)
