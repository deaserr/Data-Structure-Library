/* - Programmer: Dean Serrano
 - Class: CS240
 - Date: 04/23/2024
 - Data Structures HW 2: Stacks and Queues
 - Purpose: This program implements linear and binary search in a recursive manner
 - Issues in the code: The program runs as expected but a warning is displayed stating
 "default member initializer for non-static data member is a C++11 extension [-Wc++11-extensions]". 
 Currently the program is not user friendly as there is no implementation for user input.
 */

#include <iostream>

using namespace std;

const int MAX_SIZE = 100; //initialized size for stack

//stack class
class Stack
{
private:
    int arr[MAX_SIZE];
    int stackSize;

public:
    Stack()
    {
        stackSize = -1;//indicates an empty stack
    }

    //checks if the stack is empty
    bool isEmpty()
    {
        return (stackSize == -1);
    }//end of isEmpty

    //checks if stack is full
    bool isFull()
    {
        return (stackSize == MAX_SIZE - 1);
    }//end of isFull

    //places desired element on the stack
    void push(int element)
    {
        if(!isFull())
        {
            stackSize++;
            arr[stackSize] = element;
            cout << element << " was pushed onto the stack." << endl;
        }
        else
        {
            cout << "The stack is currently full. Your element could not be pushed." << endl;
        }
    }//end of push

    //returns and removes top element of stack
    void pop()
    {
        if(!isEmpty())
        {
            int poppedElement = arr[stackSize];
            stackSize--; //reduces current size of stack
            cout << poppedElement << " was popped from the the stack." << endl;
        }
        else
        {
            cout << "The stack is empty so nohin can be popped." << endl;
        }
    }//end of pop

    //returns top element from the stack
    int peek()
    {
        if(!isEmpty())
        {
            cout << arr[stackSize] << " is currently at the top of the stack." << endl; 
            return 0;
        }
        else
        {
            cout << "The stack is empty so there is nothing to peek at." << endl;
            return -1;
        }
    }//end of peek
};//end of stack class

//node class
class Node//doubly linked list node ofr queue creation
{
public:
    int value;
    Node* next;
    Node* prev;
};//node class

//queue class
class Queue
{
public:

    Node* head = nullptr;
    Node* tail = nullptr;

    //creates first element in queue, or adds new element to the tail of the queue
    void enqueue(int value)
    {
        Node* node = new Node;
        if(isEmpty()) //creates the first element in the queue
        {
            node -> value = value;
            node -> next = nullptr;
            node -> prev = nullptr;
            head = node;
            tail = node;
        }
        else //adds new element to the tail of the queue
        {
            node -> value = value;
            node -> next = tail;
            node -> prev = nullptr;
            tail -> prev = node;
            tail = node;
        }
    }//end of enqueue

    //removes the element at the head of the queue
    void dequeue()
    {
        if(isEmpty())
        {
            cout << "There are no elements within this queue" << endl;
        }
        else
        {
            Node* temp = new Node;
            temp = head -> prev;
            temp -> next = nullptr;
            head -> prev = nullptr;
            head = temp;
        }
    }//end of dequeue

    //checks if the queue is empty
    bool isEmpty()
    {
        if(head == NULL && tail == NULL)
        {
            return true;
        }
        else
        {
            return false;
        }
    }//end of isEmpty

    //returns the front element of the queue
    void peek()
    {
        if(isEmpty())
        {
            cout << "The queue is currently empty" << endl;
        }
        else
        {
            cout << head -> value << " is currently at the front of the queue." << endl;
        }
    }//end of peek
};

int main()
{
    //stack creation and test
    Stack stackTest;

    if(stackTest.isEmpty())
    {
        cout << "The stack is currently empty" << endl;
    }

    stackTest.push(3);
    stackTest.push(6);
    stackTest.push(10);
    stackTest.push(14);

    stackTest.pop();

    stackTest.peek();

    if(stackTest.isFull())
    {
        cout << "The stack is currently full." << endl;
    }
    else
    {
        cout << "The stack is not full." << endl;
    }

    //queue creation and test
    Queue queueTest;

    queueTest.enqueue(3);
    queueTest.enqueue(6);
    queueTest.enqueue(7);

    queueTest.dequeue();

    queueTest.peek();

    if(queueTest.isEmpty())
    {
        cout<<"Your queue is empty"<<endl;
    }
    else
    {
        cout<<"Your queue is NOT empty"<<endl;
    }

    return 0;
}//end of main