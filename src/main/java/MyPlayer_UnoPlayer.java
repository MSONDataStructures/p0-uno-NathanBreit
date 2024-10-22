import java.util.List;

public class MyPlayer_UnoPlayer implements UnoPlayer
{
    public int play(List<Card> hand, Card upCard,
                    Color calledColor, GameState state)
    {
        GameState playedCards = new GameState();
        GameState deckSize = new GameState();
        if (deckSize.getNumCardsInHandsOfUpcomingPlayers()[0] < 4)  {
            boolean cardW4 = false;
            int locationOfW4 = -1;
            boolean cardAdd2 = false;
            int locationOfAdd2 = -1;
            for (int i = 0; i < hand.size(); i++) {
                if (canPlayOn(hand.get(i), upCard, calledColor) && hand.get(i).getRank() == Rank.WILD_D4)
                {
                    locationOfW4 = i;
                    cardW4 = true;
                } else if (canPlayOn(hand.get(i), upCard, calledColor) && hand.get(i).getRank() == Rank.DRAW_TWO) {
                    locationOfAdd2 = i;
                    cardAdd2 = true;
                }
            }
            if (cardAdd2) return locationOfAdd2;
            if (cardW4) return locationOfW4;
        }

        for (int i = 0; i < hand.size(); i++) {
            if (canPlayOn(hand.get(i), upCard, calledColor) && hand.get(i).getColor() == maxColorInHand(hand))
            {
                return i;
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            if (canPlayOn(hand.get(i), upCard, calledColor) && hand.get(i).getColor() == maxColorPlayed(playedCards.getPlayedCards()))
            {
                return i;
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            if (canPlayOn(hand.get(i), upCard, calledColor) && hand.get(i).getColor() != Color.NONE)
            {
                return i;
            }
        }
        for (int i = 0; i < hand.size(); i++) {
            if (canPlayOn(hand.get(i), upCard, calledColor)) {
                return i;
            }
        }
        return -1;
    }

    public Color callColor(List<Card> hand)
    {
        return maxColorInHand(hand);
    }
    private Color maxColorPlayed(List<Card> getPlayedCards) {
        int[] arr = new int[4]; //RYGB
        int max = 0;
        Color maxColorReturn = Color.RED;
        for (int i = 0; i < getPlayedCards.size(); i++) {
            if (getPlayedCards.get(i).getColor() == Color.RED) {
                arr[0] += 1;
                if (arr[0] > max) {
                    max = arr[0];
                    maxColorReturn = Color.RED;
                }
            }
            if (getPlayedCards.get(i).getColor() == Color.YELLOW) {
                arr[1] += 1;
                if (arr[1] > max) {
                    max = arr[1];
                    maxColorReturn = Color.YELLOW;
                }
            }
            if (getPlayedCards.get(i).getColor() == Color.GREEN) {
                arr[2] += 1;
                if (arr[2] > max) {
                    max = arr[2];
                    maxColorReturn = Color.GREEN;
                }
            }
            if (getPlayedCards.get(i).getColor() == Color.BLUE) {
                arr[3] += 1;
                if (arr[3] > max) {
                    max = arr[3];
                    maxColorReturn = Color.BLUE;
                }
            }
        }
        return maxColorReturn;
    }

    private Color maxColorInHand(List<Card> hand) {
        int[] arr = new int[4]; //RYGB
        int max = 0;
        Color maxColorReturn = Color.RED;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getColor() == Color.RED) {
                arr[0] += 1;
                if (arr[0] > max) {
                    max = arr[0];
                    maxColorReturn = Color.RED;
                }
            }
            if (hand.get(i).getColor() == Color.YELLOW) {
                arr[1] += 1;
                if (arr[1] > max) {
                    max = arr[1];
                    maxColorReturn = Color.YELLOW;
                }
            }
            if (hand.get(i).getColor() == Color.GREEN) {
                arr[2] += 1;
                if (arr[2] > max) {
                    max = arr[2];
                    maxColorReturn = Color.GREEN;
                }
            }
            if (hand.get(i).getColor() == Color.BLUE) {
                arr[3] += 1;
                if (arr[3] > max) {
                    max = arr[3];
                    maxColorReturn = Color.BLUE;
                }
            }
        }
        return maxColorReturn;
    }
    public boolean canPlayOn(Card card, Card upCard,
                             Color calledColor)
    {
        // is that card even legal to play?
        boolean result = false;
        result |= card.getRank() == Rank.WILD;
        result |= card.getRank() == Rank.WILD_D4;
        result |= card.getColor() == upCard.getColor();
        result |= card.getColor() == calledColor;
        result |= (card.getRank() == upCard.getRank() &&
                card.getRank() != Rank.NUMBER);
        result |= (card.getNumber() == upCard.getNumber() &&
                card.getRank() == Rank.NUMBER &&
                upCard.getRank() == Rank.NUMBER);
        return result;
    }
}
