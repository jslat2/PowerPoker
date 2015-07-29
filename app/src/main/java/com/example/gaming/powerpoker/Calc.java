package com.example.gaming.powerpoker;
import java.util.ArrayList;

public class Calc{


	public static int[] space = {1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 1, 10, 1, 11, 1, 12,
			2, 0, 2, 1, 2, 2, 2, 3, 2, 4, 2, 5, 2, 6, 2, 7, 2, 8, 2, 9, 2, 10, 2, 11, 2, 12,
			3, 0, 3, 1, 3, 2, 3, 3, 3, 4, 3, 5, 3, 6, 3, 7, 3, 8, 3, 9, 3, 10, 3, 11, 3, 12,
			4, 0, 4, 1, 4, 2, 4, 3, 4, 4, 4, 5, 4, 6, 4, 7, 4, 8, 4, 9, 4, 10, 4, 11, 4, 12};

	//signals as to whether a calculation is ongoing
	public static boolean calculating = true;


	public void beginCalc(){
		calculating = true;
	}

	public void endCalc(){
		calculating = false;
	}

	//used as placeholder for result of calculation as it currently stands
	//some functions take  a while to process, so this holds the interim result
	public static double result = 1;

	//Method assigns a hand value to any flushes or straight flushes given the input card ArrayList
	//returns ArrayList containing single entry of '0' if there is no flush/straight flush
	public ArrayList flushRank(int[] cards){
		int clubs = 0;
		int diamonds = 0;
		int hearts = 0;
		int spades = 0;
		int highSuit = 0;
		int straightFlushStart = -1;
		ArrayList none = new ArrayList<Integer>();
		ArrayList done = new ArrayList<Integer>();
		none.add(0);


		//check to see if there is 5 of one suit
		for(int i = 0; i<7; i++){
			if(cards[i*2] == 1){
				clubs++;
				if(clubs>=5){
					highSuit = 1;
				}
			}

			if(cards[i*2] == 2){
				diamonds++;
				if(diamonds>=5){
					highSuit = 2;
				}
			}

			if(cards[i*2] == 3){
				hearts++;
				if(hearts>=5){
					highSuit = 3;
				}
			}

			if(cards[i*2] == 4){
				spades++;
				if(spades>=5){
					highSuit = 4;
				}
			}
		}

		if(highSuit == 0){
			return none;
		}

		//Arrange suited cards of the flush suit in order largest to smallest
		ArrayList holder = new ArrayList<Integer>();
		for(int i = 0; i < 7; i++){
			if(cards[i*2] == highSuit){
				if(holder.size() == 0){
					holder.add((Integer)cards[i*2+1]);
				}
				else{
					for(int j = 0; j < holder.size(); j++){
						if(cards[i*2+1] > (int)holder.get(j)){
							holder.add(j, cards[i*2+1]);
							break;
						}
						if(j == holder.size()-1){
							holder.add(holder.size(), cards[i*2+1]);
							break;
						}
					}
				}
			}

		}

		if(holder.get(0) == (Integer)12){
			holder.add((Integer)(-1));
		}

		//Check for straight flushes
		for(int i = 0; i < holder.size()-4; i++){
			if((int)holder.get(i) == (int)holder.get(i+1)+1){
				if((int)holder.get(i+1) == (int)holder.get(i+2)+1){
					if((int)holder.get(i+2) == (int)holder.get(i+3)+1){
						if((int)holder.get(i+3) == (int)holder.get(i+4)+1){
							straightFlushStart = i;
							break;
						}
					}
				}
			}
		}

		//If no straight flushes return flush value
		if(straightFlushStart == -1){
			done.add(5);
			for(int i = 0; i < 5; i++){
				done.add(holder.get(i));
			}
			return done;
		}

		//If there is a straight flush return rank 8 and the highest card in the straight flush
		//no need to code for the lower ranks in the straight flush as they do not contribute
		//to the comparison value
		else if(straightFlushStart != -1){
			done.add(8);
			done.add(holder.get(straightFlushStart));
			return done;
		}
		return none;
	}

	public ArrayList straightRank(int[] cards){

		int straightStart = -1;

		ArrayList holder = new ArrayList<Integer>();
		ArrayList none = new ArrayList<Integer>();
		ArrayList done = new ArrayList<Integer>();
		none.add((Integer)0);
		holder.add(cards[1]);

		for(int i = 1; i < 7; i++){
			for(int j = 0; j < holder.size(); j++){
				//don't add cards of same rank
				if(cards[i*2+1] == (int)holder.get(j)){
					break;
				}
				if(cards[i*2+1] > (int)holder.get(j)){
					holder.add(j, cards[i*2+1]);
					break;
				}
				if(j == holder.size()-1){
					holder.add(holder.size(), cards[i*2+1]);
					break;
				}
			}
		}

		if(holder.get(0) == (Integer)12){
			holder.add((Integer)(-1));
		}
		for(int i = 0; i < holder.size()-4; i++){
			if((int)holder.get(i) == (int)holder.get(i+1)+1){
				if((int)holder.get(i+1) == (int)holder.get(i+2)+1){
					if((int)holder.get(i+2) == (int)holder.get(i+3)+1){
						if((int)holder.get(i+3) == (int)holder.get(i+4)+1){
							straightStart = i;
							break;
						}
					}
				}
			}
		}

		if(straightStart == -1){
			return none;
		}

		else{
			done.add(4);
			done.add(holder.get(straightStart));
			}

		return done;
	}

	public ArrayList pairRank(int[] cards){

		ArrayList threes = new ArrayList<Integer>();
		ArrayList twos = new ArrayList<Integer>();

		ArrayList holder = new ArrayList<Integer>();
		ArrayList done = new ArrayList<Integer>();
		int[] ranks = new int[7];
		int[] histogram = new int[13];
		for(int i =0; i < 12; i++){
			histogram[i] = 0;
		}

		for(int i = 0; i < 7; i++){
			ranks[i] = cards[i*2+1];
		}

		holder.add(ranks[0]);

		//Arrange cards by rank largest to smallest
		for(int i = 1; i < 7; i++){
			for(int j = 0; j < holder.size(); j++){
				if(ranks[i] > (int)holder.get(j)){
					holder.add(j, ranks[i]);
					break;
				}
				if(j == holder.size()-1){
					holder.add(holder.size(), ranks[i]);
					break;
				}
			}
		}

		//Make a histogram of the card ranks starting with high cards
		for(int i = 0; i < 7; i ++){
			if((int)holder.get(i) == 12){
				histogram[0]++;
			}
			if((int)holder.get(i) == 11){
				histogram[1]++;
			}
			if((int)holder.get(i) == 10){
				histogram[2]++;
			}
			if((int)holder.get(i) == 9){
				histogram[3]++;
			}
			if((int)holder.get(i) == 8){
				histogram[4]++;
			}
			if((int)holder.get(i) == 7){
				histogram[5]++;
			}
			if((int)holder.get(i) == 6){
				histogram[6]++;
			}
			if((int)holder.get(i) == 5){
				histogram[7]++;
			}
			if((int)holder.get(i) == 4){
				histogram[8]++;
			}
			if((int)holder.get(i) == 3){
				histogram[9]++;
			}
			if((int)holder.get(i) == 2){
				histogram[10]++;
			}
			if((int)holder.get(i) == 1){
				histogram[11]++;
			}
			if((int)holder.get(i) == 0){
				histogram[12]++;
			}
		}

		//Scan the histogram for quads and return quad rank if applicable, add in kicker
		for(int i = 0; i < 13; i++){
			if(histogram[i] == 4){
				done.add(7);
				done.add(12-i);
				for(int j = 0; j < histogram.length; j++){
					if(histogram[j] != 0 && (histogram[j] != 4)){
						done.add(12-j);
						break;
					}
				}
				return done;
			}
		}

		//Scan for triples and add to an arraylist
		for(int i = 0; i < 13; i++){
			if(histogram[i] == 3){
				threes.add((Integer)(12-i));
			}
		}

		//Scan for pairs and add to an arraylist (disregarding a third pair if there is one)
		for(int i = 0; i < 13; i++){
			if(histogram[i] == 2){
				twos.add((Integer)(12-i));
				if(twos.size() == 2){
					break;
				}
			}
		}

		//Check for full houses, including two three of a kinds or 1 three of a kind and at least one pair
		if(threes.size() > 1 || (threes.size() > 0 && twos.size() > 0)){
			done.add(6);
			done.add(threes.get(0));
			if(threes.size() > 1 && twos.size() > 0){
				if((int)threes.get(1) > (int)twos.get(0)){
					done.add(threes.get(1));
				}
				else{
					done.add(twos.get(0));
				}
				return done;
			}

			else if(threes.size() > 1){
				done.add(threes.get(1));
				return done;
			}

			done.add(twos.get(0));
			return done;
		}

		//Check for three of a kind, add in kickers and return
		else if(threes.size() > 0){
			done.add(3);
			done.add(threes.get(0));

			//add kicker
			for(int i = 0; i < holder.size(); i++){
				if((int)holder.get(i) != (int)threes.get(0)){
					done.add(holder.get(i));
				}
				if(done.size() == 4){
					break;
				}
			}
			return done;
		}

		//Check for two pair, add in kickers and return
		else if(twos.size() > 1){
			done.add(2);
			done.add(twos.get(0));
			done.add(twos.get(1));

			//add kickers
			for(int i = 0; i < holder.size(); i++){
				if((int)holder.get(i) != (int)twos.get(0)  && ((int)holder.get(i) != (int)twos.get(1))){
					done.add(holder.get(i));
					break;
				}
			}
			return done;

		}

		//Check for pairs add in kickers and return
		else if(twos.size() == 1){
			done.add(1);
			done.add(twos.get(0));
			for(int i = 0; i < holder.size(); i++){
				if(holder.get(i) != twos.get(0)){
					done.add(holder.get(i));
					if(done.size() == 5){
						break;
					}
				}

			}
			return done;
		}

		//If none of the above apply add 0 rank and add in 5 kickers
		done.add(0);
		for(int i = 0; i < 5; i++){
			done.add(holder.get(i));
		}

		return done;
	}


	public ArrayList handRank(int[] cards){

		if((int)flushRank(cards).get(0) != 0){
			return flushRank(cards);
		}

		else if((int)straightRank(cards).get(0) != 0){
			return straightRank(cards);
		}

		return pairRank(cards);
	}

	public double determineWinner(ArrayList cards, ArrayList opp){
		for(int i = 0; i < cards.size(); i++){
			if((int)cards.get(i) > (int)opp.get(i)){
				return 1;
			}
			else if((int)opp.get(i) > (int)cards.get(i)){
				return 0;
			}
		}
		return .5;
	}

	//calculate the equity in your hand
	public double equity(int[] cards, int[] opp, int[] board){

		double count = 0;
		boolean newCard = true;

		int[] cardsTotal = new int[14];
		int[] oppTotal = new int[14];

		for(int i = 0; i < 4; i++){
			cardsTotal[i] = cards[i];
			oppTotal[i] = opp[i];
		}
		if(board != null){
			for(int i = 0; i < board.length; i++) {
				cardsTotal[i+4] = board[i];
				oppTotal[i+4] = board[i];
			}
		}



		//take cards out of space for potential cards to come
		int[] remainingCards;
		if(board != null) {
			remainingCards = new int[100 - board.length];
		}
		else{
			remainingCards =  new int[100];
		}

		int counter = 0;

		for(int i = 0; i < 103; i = i +2){
			for(int j = 0; j < cards.length - 1; j = j+2){
				if(cards[j] == space[i] && cards[j+1] == space[i+1]){
					newCard = false;
				}
			}
			for(int j = 0; j < opp.length - 1; j = j+2){
				if(opp[j] == space[i] && opp[j+1] == space[i+1]){
					newCard = false;
				}
			}
			if(board != null){
				for(int j = 0; j < board.length - 1; j = j+2) {
					if (board[j] == space[i] && board[j + 1] == space[i + 1]) {
						newCard = false;
					}
				}
			}
			//only add the card to remaining if it is not one already on board or in a hand
			if(newCard){
				remainingCards[counter] = space[i];
				remainingCards[counter+1] = space[i+1];
				counter+=2;
			}

			//reset the boolean
			newCard = true;
		}

		if(board == null) {
			for (int i = 0; i < 87; i = i + 2) {
				cardsTotal[4] = remainingCards[i];
				cardsTotal[5] = remainingCards[i + 1];
				oppTotal[4] = remainingCards[i];
				oppTotal[5] = remainingCards[i + 1];

				for (int j = i + 2; j < 89; j = j + 2) {
					cardsTotal[6] = remainingCards[j];
					cardsTotal[7] = remainingCards[j + 1];
					oppTotal[6] = remainingCards[j];
					oppTotal[7] = remainingCards[j + 1];

					for(int k = j + 2; k < 91; k = k+2) {
						cardsTotal[8] = remainingCards[k];
						cardsTotal[9] = remainingCards[k + 1];
						oppTotal[8] = remainingCards[k];
						oppTotal[9] = remainingCards[k + 1];

						for(int l = k + 2; l < 93; l = l+2) {
							cardsTotal[10] = remainingCards[l];
							cardsTotal[11] = remainingCards[l + 1];
							oppTotal[10] = remainingCards[l];
							oppTotal[11] = remainingCards[l + 1];

							for(int m = l +2; m < 95; m = m+2) {
								cardsTotal[12] = remainingCards[m];
								cardsTotal[13] = remainingCards[m + 1];
								oppTotal[12] = remainingCards[m];
								oppTotal[13] = remainingCards[m + 1];

								count += determineWinner(handRank(cardsTotal), handRank(oppTotal));

							}
						}
					}
				}
			}
			return count;
		}

		if(board.length == 6) {
			for (int i = 0; i < 87; i = i + 2) {
				cardsTotal[10] = remainingCards[i];
				cardsTotal[11] = remainingCards[i + 1];
				oppTotal[10] = remainingCards[i];
				oppTotal[11] = remainingCards[i + 1];

				for (int j = i + 2; j < 89; j = j + 2) {
					cardsTotal[12] = remainingCards[j];
					cardsTotal[13] = remainingCards[j + 1];
					oppTotal[12] = remainingCards[j];
					oppTotal[13] = remainingCards[j + 1];


					count += determineWinner(handRank(cardsTotal), handRank(oppTotal));

					System.out.println("myhand " + cardsTotal + " rank " + handRank(cardsTotal));
					System.out.println("hishand " + oppTotal + " rank " + handRank(oppTotal));

					System.out.println(count);
				}
			}
			return count;
		}

		else{
			for (int i = 0; i < 87; i = i + 2) {
				cardsTotal[12] = remainingCards[i];
				cardsTotal[13] = remainingCards[i + 1];
				oppTotal[12] = remainingCards[i];
				oppTotal[13] = remainingCards[i + 1];

				count += determineWinner(handRank(cardsTotal), handRank(oppTotal));

				System.out.println(count);
			}
			return count;
		}
	}

	public void check(int[] cards, int[] opp, int[] board){
		System.out.println(equity(cards, opp, null) + "!!!!!!!!!!!!!!!!!!!!!!");
	}
}