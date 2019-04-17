package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;

public class QueueUsingStack<T> implements Queue<T> {

   private Stack<T> stack1;
   private Stack<T> stack2;

   public QueueUsingStack(int size) {
      stack1 = new StackImpl<T>(size);
      stack2 = new StackImpl<T>(size);
   }

   @Override
   public void enqueue(T element) throws QueueOverflowException {
      if (stack1.isFull())
         throw new QueueOverflowException();

      try {
         stack1.push(element);
      } catch (StackOverflowException e) {
         e.printStackTrace();
      }
   }

   @Override
   public T dequeue() throws QueueUnderflowException {
      if (stack1.isEmpty())
         throw new QueueUnderflowException();

      T result = null;

      try {
         while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
         }

         result = stack2.pop();

         while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

      return result;
   }

   @Override
   public T head() {
      T result = null;

      if (!stack1.isEmpty()) {
         try {
            while (!stack1.isEmpty()) {
               stack2.push(stack1.pop());
            }

            result = stack2.top();

            while (!stack2.isEmpty()) {
               stack1.push(stack2.pop());
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }

      return result;
   }

   @Override
   public boolean isEmpty() {
      return stack1.isEmpty();
   }

   @Override
   public boolean isFull() {
      return stack1.isFull();
   }

}
