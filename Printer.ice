module Demo
{
    interface Printer
    {
        string printString(string s);
    }
    
    interface CallbackReceiver
    {
        void callback(string s);
        void update();
    }

    interface CallbackSender
    {
        void login(string hostName, CallbackReceiver* proxy);
        string printString(string s);
        void notify();
    }

    interface MergeSortI
    {
        void mergeSort(string [] arr, int left, int right);
        void merge(string [] arr, int left, int mid, int right);
    }
}