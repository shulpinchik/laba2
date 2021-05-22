import java.util.ArrayList;
import java.util.Scanner;

class ReHashMap<K, V>
{

    class MapNode<K, V>
    {

        K key;
        V value;
        MapNode<K, V> next;


        public MapNode(K key, V value)
        {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    ArrayList<MapNode<K, V>> buckets;

    // No. of pairs stored - n
    int size;

    // Size of the bucketArray - b
    int numBuckets;

    // Default loadFactor
    final double DEFAULT_LOAD_FACTOR = 0.75;

    public ReHashMap()
    {
        numBuckets = 5;

        buckets = new ArrayList<>(numBuckets);

        for (int i = 0; i < numBuckets; i++)
        {
            // Initialising to null
            buckets.add(null);
        }
        System.out.println("Size of Map: " + numBuckets);
        System.out.println("Default Load Factor : " + DEFAULT_LOAD_FACTOR + "\n");
    }

    private int getBucketId(K key)
    {

        int hashCode = key.hashCode();

        return (hashCode % numBuckets);
    }

    public void insert(K key, V value)
    {
        int bucketId = getBucketId(key);

        MapNode<K, V> head;
        MapNode<K, V> newElementNode = new MapNode<K, V>(key, value);

        head = buckets.get(bucketId);
        newElementNode.next = head;

        buckets.set(bucketId, newElementNode);

        System.out.println("Pair(" + key + ", " + value + ") inserted");

        size++;

        double loadFactor =  size*1.0 / numBuckets;
        System.out.println("Load factor = " + loadFactor);

        //check for rehash
        if (loadFactor > DEFAULT_LOAD_FACTOR)
        {
            System.out.println("Load factor rather then 0.75, rehashing");
            rehash();

            System.out.println("New Size of Map: " + numBuckets + "\n");
        }
        else
            System.out.println("Size of Map: " + numBuckets + "\n");
    }

    private void rehash()
    {
        ArrayList<MapNode<K, V>> temp = buckets;

        buckets = new ArrayList<MapNode<K, V>>(2 * numBuckets);

        for (int i = 0; i < 2 * numBuckets; i++)
        {
            // Initialised to null
            buckets.add(null);
        }
        size = 0;
        numBuckets *= 2;

        for (int i = 0; i < temp.size(); i++)
        {

            MapNode<K, V> head = temp.get(i);

            while (head != null)
            {
                K key = head.key;
                V val = head.value;

                insert(key, val);
                head = head.next;
            }
        }
        System.out.println("Rehashing Done \n");
    }

    public void printMap()
    {

        // The present bucket list is made temp
        ArrayList<MapNode<K, V>> temp = buckets;

        System.out.println("Current HashMap:");
        // loop through all the nodes and print them
        for (int i = 0; i < temp.size(); i++)
        {

            MapNode<K, V> head = temp.get(i);

            while (head != null)
            {
                System.out.println("key = " + head.key + ", val = " + head.value);

                head = head.next;
            }
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        Scanner scanner= new Scanner(System.in);
        int count=1;
        ReHashMap<Integer, String> reHashMap = new ReHashMap<Integer, String>();

        String word;

        do
        {
            System.out.println("Insert object for HashMap, '0' for exit");
            word=scanner.nextLine();
            if (!word.equals("0"))
            {
                reHashMap.insert(count, word);
                reHashMap.printMap();
                count++;
            }
        } while (!word.equals("0"));

    }
}

