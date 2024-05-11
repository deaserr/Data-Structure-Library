/* - Programmer: Dean Serrano
 - Class: CS240
 - Date: 04/17/2024
 - Data Structures HW 1: Linked Lists
 - Purpose: This program can take in a .txt file with a series of integers to be placed in a single linked list.
    There is functionality to insert, delete, and reead the created list.
 - Issues in the code: I was unable to fully implement the full functionalities outlined within the assignemt. 
    The delete method is unable to correctly reconnect the existing nodes
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class DoubleLL {
    
    static Scanner input = new Scanner(System.in);

    private static int length; //Keeps track of the number of objects in list

    // Class for making node
    public static class Node 
    {
        private Node prev;
        private Node next;

        private int value;
        private int index;

        // Constructor method
        public Node(int value, int index)
        {
            this.value = value;
            this.index = index;
        } // End of Node
    } // End of contructor class

    private static Node front;

    public static void main(String args[]) throws FileNotFoundException
    {
        File file  = new File("/Volumes/Sam2/SchoolFiles/Code/CS240/Week2/numbers-2.txt");
        Scanner fileScan = new Scanner(file);

        while(fileScan.hasNextInt())
        {
            int value = fileScan.nextInt();
            Node node = new Node(value, length);
            node.next = null;

            if(front.next == null) //Adds new node to empty list
            {
                front = node; //Sets front to new node
                length++;
            }
            else //Adds a node to the end of the list
            {
                Node last = null;
                Node current = front;
                while(current.next != null) //Checks for the end of the list
                {
                    last = current;
                    current = current.next;
                }
                current.prev = last;
                current.next = node;
                length++;
            }
        }

        //For user command
        int exit = 0;
        while(exit == 0)
        {
            System.out.print("Would you like to insert a new value(1), delete a value(2), view your list(3), or quit the program (4): ");
            int userCommand = input.nextInt();

            switch(userCommand)
            {
                case 1:
                    insert();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    readList();
                    break;
                case 4:
                    exit++;
                    break;
            }
        }
    }

    public static void readList()
    {
        Node toPrint = front;
        while(toPrint != null)
        {
            System.out.println(toPrint.value);
            toPrint = toPrint.next;
        }
    }
    
    // Method add new entry to list
    public static void insert()
    {
        //Gets new value from the user
        System.out.print("Please enter the value you would like to insert: ");
        int userValue = input.nextInt();

        Node node = new Node(userValue, length);
        node.next = null;

        if(front == null) //Adds new node to empty list
        {
            front = node; //Sets front to new node
            length++;
        }
        else //Adds a node to the end of the list
        {
            Node last = null;
            Node current = front;
            while(current.next != null) //Checks for the end of the list
            {
                last = current;
                current = current.next; 
            }
            current.prev = last;
            current.next = node;
            length++;
        }
    } // End of add

    // Method deletes a value at a specified index
    public static void delete()
    {
        System.out.print("At would index would you like to perform a delete: ");
        int index = input.nextInt();

        if(index == 0)
        {
            front = front.next;
        }
        else
        {
            Node current = front;
            Node n1 = null;
            for(int i = 0; i < index - 1; i++) //Moves through list til defined value is found
            {
                current = current.next; // Sets current to the found entry
            }
            // Removes desired value and reconnects the surrounding nodes
            n1 = current.next;
            current.next = n1.next;

            //Adjusts node's indexes
            while(current.next != null)
            {
                current.index = current.index - 1;
                current = current.next;
            }
            System.out.println("The value at index has been removed.");
        }
    } // End of delete

    /*Selection sort method
    void selectionSort()
    {
        Node current = front;
        for(int i = 0; i < length - 1; i++)
        {
            while(current.index == i) //checks if the current node is the end of the sorted portion of list
            {
                int min = current.value;
                int indexOfMin = current.index;
                for(int j = i + 1; j < length; j++)
                {
                    //if/else statement to search for the lowest value in the unsorted portion of list
                }
                //swap the lowest value you found with the first value in the unsorted portion of list
            }
        }

    } End of selectionSort*/
}
