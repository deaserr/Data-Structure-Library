/* - Programmer: Dean Serrano
 - Class: CS240
 - Date: 04/30/2024
 - Alogorithm HW 4: Merge & Quicksort
 - Purpose: This program sorts a given data set with the options for both merge sort and quick sort methods
 - Issues in the code: None detected
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QMSort {
    
    public static void main(String[] args) throws FileNotFoundException
    {
        int arrSize = 3999; //number of element in test file, numbers-4.txt, set for array size
        int[] array = new int[arrSize];
        int index = 0; //counter to place values from test file into array

        File file = new File("numbers-4.txt");
        Scanner scan = new Scanner(file);

        //places values from the file into an array
        while(scan.hasNextInt())
        {
            int value = scan.nextInt();
            array[index] = value;
            index++;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("What sorting method would you like to use, merge sort (1), or quick sort (2): ");
        int userCommand = input.nextInt();

        switch(userCommand)
        {
            case 1:
                mergeSort(array);
                System.out.println("Merge sort complete!");
                break;
            case 2:
                quickSort(array, 0, array.length - 1);
                System.out.println("Quick sort complete!");
                break;
            default:
                System.out.println("Sorry, that is an invalid input.");
        }

        System.out.print("What value would you like to search for?: ");
        int key = input.nextInt();

        int ans = linearSearch(array, arrSize, key);

        if(ans == -1) //when number can't be found
        {
            System.out.println("The number " + key + " could not be found.");
        }
        else //when number is found
        {
            System.out.println("After the file was sorted, the number " + key + " was found at the index " + ans + ".");
        }

    }//end of main

    //merge sort method
    public static void mergeSort(int[] inputArray)
    {
        int inputLength = inputArray.length;

        if(inputLength < 2) //base case
        {
            return;
        }

        int midIndex = inputLength / 2;
        //arrays to split main array for sorting
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        //gets left half of array
        for(int i = 0; i < midIndex; i++)
        {
            leftHalf[i] = inputArray[i];
        }
        
        //gets right half of array
        for(int i = midIndex; i < inputLength; i++)
        {
            rightHalf[i - midIndex] = inputArray[i];
        }

        //sorts each new portion
        mergeSort(leftHalf);
        mergeSort(rightHalf);

        //merges portioned sections back together
        merge(inputArray, leftHalf, rightHalf);
    }//end of merge sort

    //method to merge portioned arrays together into main array
    public static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf)
    {
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;

        //variables to increment through portioned arrays and main array
        int i = 0, j = 0, k = 0;

        //switches between left and right portions to add sorted elements to main array
        while(i < leftSize && j < rightSize)
        {
            if(leftHalf[i] <= rightHalf[j])
            {
                inputArray[k] = leftHalf[i];
                i++;
            }
            else
            {
                inputArray[k] = rightHalf[j];
                j++;
            }
            k++;
        }

        //handles left over elments in first portion
        while(i < leftSize)
        {
            inputArray[k] = leftHalf[i];
            i++;
            k++;
        }

        //handles left over elments in second portion
        while(j < rightSize)
        {
            inputArray[k] = rightHalf[j];
            j++;
            k++;
        }
    }//end of merge

    //quick sort method
    public static void quickSort(int[] array, int lowIndex, int highIndex)
    {
        //base case
        if(lowIndex >= highIndex)
        {
            return;
        }

        //element for comparison
        int pivot  = array[highIndex];

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quickSort(array, lowIndex, leftPointer - 1);
        quickSort(array, leftPointer + 1, highIndex);
    }//end of quick sort

    //partition method to sort array around pivot
    public static int partition(int[] array, int lowIndex, int highIndex, int pivot)
    {
        int leftPointer = lowIndex;
        int rightPointer = highIndex;

        //sorts elements around pivot point
        while(leftPointer < rightPointer)
        {
            while(array[leftPointer] <= pivot && leftPointer < rightPointer)
            {
                leftPointer++;
            }

            while(array[rightPointer] >= pivot && leftPointer < rightPointer)
            {
                rightPointer--;
            }

            swap(array, leftPointer, rightPointer);   
        }

        swap(array, leftPointer, highIndex);
        return leftPointer;
    }//end of partition method

    //swaps the place of two elements in the given array
    public static void swap(int[] array, int index1, int index2)
    {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }//end of swap

    //linear search method
    public static int linearSearch(int[] array, int size, int key)
    {
        if(size == 0)
        {
            return -1; //if the value cannot be found
        }
        else if(array[size - 1] == key) //element found
        {
            return size - 1;
        }
        return linearSearch(array, size - 1, key);
    }//end of linear search method
}