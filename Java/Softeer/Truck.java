package Softeer;
import java.io.*;
import java.util.*;


// 너무 어렵게 느껴졌던 문제
// O(P log P + Q log (q) +)
public class Truck {


    static int N;
    static int Q;

    public static class Proposal implements Comparable<Proposal> {

        int size;
        int cost;
        int customer;
        public Proposal(int size, int cost, int customer) {
            this.size = size;
            this.cost = cost;
            this.customer = customer;
        }
        
        // 오름차순
        public int compareTo(Proposal p) {
            return Integer.compare(this.size, p.size);
        }
    }

    public static class Query implements Comparable<Query> {
        int profit;
        int index;

        public Query(int profit, int index) {
            this.profit = profit;
            this.index = index;
        }

        public int compareTo(Query o) {
            return Integer.compare(this.profit, o.profit);
        }
    }

    public static class Result implements Comparable<Result> {
        int index, size;

        public Result(int index, int size) {
            this.index = index;
            this.size = size;
        }

        public int compareTo(Result o) {
            return Integer.compare(this.index, o.index);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        List<Proposal> proposals = new ArrayList<Proposal>();
        List<Query> queries = new ArrayList<Query>();
        int[] maxOfferPerCustomers = new int[100_002];

        Arrays.fill(maxOfferPerCustomers, 0);


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int number = Integer.parseInt(st.nextToken());

            for (int j = 0; j < number; j++) {
                int size = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                proposals.add(new Proposal(size, cost, i));
            }
        }
        Collections.sort(proposals);

        Q = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            queries.add(new Query(Integer.parseInt(st.nextToken()), i));
        }

        Collections.sort(queries);

        // Solution
        List<Result> results = new ArrayList<Result>();
        long cost_sum = 0;
        int cur = 0;
        int cur_size = -1;

        
        // Query, Propsal 을 정렬 해서 낮은 순부터 위로 순차적으로 진행함 (Greedy)
        for (Query query : queries) {
            int profit = query.profit;

            while (cost_sum < profit) {

                // 매출 못 채운다.
                if (cur == proposals.size()) {
                    cur_size = -1;
                    break;
                }
                
                // proposal 도 낮은 순으로 순차적
                Proposal p = proposals.get(cur);
                cur++;
                
                // 현재 proposal 의 사용자 확인
                int customer = p.customer;
                int cur_cost = maxOfferPerCustomers[customer];

                if (cur_cost >= p.cost) continue;
                
                // 사용자의 이전 Proposal 제외하고, 지금 꺼로 추가
                cost_sum -= cur_cost;
                cost_sum += p.cost;
                maxOfferPerCustomers[customer] = p.cost;
                cur_size = p.size;
            }

            results.add(new Result(query.index, cur_size));
        }

        Collections.sort(results);

        for (Result result : results) {
            System.out.print(result.size + " ");
        }

        br.close();
    }
}
