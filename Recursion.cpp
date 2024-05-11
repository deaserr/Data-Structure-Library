/* - Programmer: Dean Serrano
 - Class: CS240
 - Date: 04/23/2024
 - Alogorithm HW 3: Recursion vs Interation
 - Purpose: This program implements linear and binary search in a recursive manner
 - Issues in the code: None detected
 */

#include <iostream>
#include <fstream>

using namespace std;

int linearSearch(int arr[], int size, int key);
int binarySearch(int arr[], int low, int high, int key);
void insertSort(int array[], int length);

int main()
{
    //Initalizes the array
    int length = 9999;
    int array[length];

    //Asks user for file to search
    string fileName;
    cout << "Please enter the name of the file you would like sorted and searched: ";
    getline(cin, fileName);

    ifstream inFile;
    
    inFile.open(fileName);

    //Handles situation where no file is found
    if(inFile.fail())
    {
        cout << "Error opening file." << endl;
        return 1;
    }

    int value;
    int position = 0;

    //Places each value from the file into the array
    while (inFile >> value)
    {
        array[position] = value;
        position++;
    }

    insertSort(array, length);

    int sortCommand;
    cout << "Would you like to have a linear(1) or binary(2) search performed?: ";
    cin >> sortCommand;

    if(sortCommand == 1 || sortCommand == 2)
    {
        //Gets the value to be search for
        int userKey;
        cout << "What value would you like to search for?: ";
        cin >> userKey;

        int ans;
        int low = 0;
        if(sortCommand == 1)
        {
            ans = linearSearch(array, length, userKey);

            if(ans == -1) //When number can't be found
            {
                cout << "The number " << userKey << " could not be found." << endl;
            }
            else //When number is found
            {
                cout << "The number " << userKey << " was found at " << ans << " index in the given array." << endl;
            }
        }
        else if(sortCommand == 2)
        {
            ans = binarySearch(array, low, length - 1, userKey);

            if(ans == -1) //When number can't be found
            {
                cout << "The number " << userKey << " could not be found." << endl;
            }
            else //When number is found
            {
                cout << "The number " << userKey << " was found at " << ans << " index in the given array." << endl;
            }
        }
    }
    else
    {
        cout << "Sorry. That is an invalid command.";
    }

    inFile.close();
    return 0;
}//end of main

//linear search method
int linearSearch(int arr[], int size, int key)
{
    if(size == 0)
    {
        return -1; //if the value cannot be found
    }
    else if(arr[size - 1] == key) //element found
    {
        return size - 1;
    }
    return linearSearch(arr, size - 1, key);
}//end of linear search method

//binary search method
int binarySearch(int arr[], int low, int high, int key)
{
    if(low < high)
    {
        int mid = (high + low) / 2;
        if(arr[mid] == key) //if the element is found in the middle
        {
            return mid;
        }
        else if(arr[mid] > key)//searches left of the middle
        {
            return binarySearch(arr, low, mid - 1, key);
        }
        else//searches right of the middle
        {
            return binarySearch(arr, mid + 1, high, key);
        }
    }
    else if(arr[low] == key) //element found
    {
        return low;
    }
    else //element not found
    {
        return -1;
    } 
}//end of binary search method

//insertion sort method
void insertSort(int array[], int length)
{
    //Base case
    if(length <= 1)
    {
        return;
    }

    insertSort(array, length - 1);

    //inserts number into sorted place
    int key = array[length - 1];
    int j = length - 2;
    while(j >= 0 && array[j] > key)
    {
        array[j + 1] = array[j];
        j -= 1;
    }
    array[j + 1] = key;
} //end of insertSort

/*
My original insertion sort method without recursion 
from Algorithms HW 2

void insertSort(int array[], int length)
{
    //moves through the array starting from the second element in the array
    for(int i = 1; i < length; i++)
    {
        int key = array[i]; //Gets the first element to compare
        int j = i - 1; //Index of the element to the left of the first element
       
        //Loops thorugh the left hand portion of array
        while(j >= 0 && array[j] > key)
        {
            array[j + 1] = array[j];
            j = j - 1;
        }
        array[j + 1] = key; //Swaps vaules
    }
} End of insertSort*/