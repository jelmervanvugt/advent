import { getRawInput, splitOnNewLine } from '../util';

const parseToCards = (input: string[]): any[] => {
  const cleanAndParse = (input: string): any[] => {
    return input.trim().replace(/\s+/g, ',').split(',');
  };
  return input.map((row) => {
    const cardValues = row.split(': ');
    const cardNo = parseInt(cardValues[0].split(' ')[1]);
    const numbers = cardValues[1].split(' | ');
    return [cardNo, cleanAndParse(numbers[0]), cleanAndParse(numbers[1]), 1];
  });
};

const calcHits = (card: any[]): number => {
  const winningNumbers = card[1];
  const numbers = card[2];
  let hits = 0;

  numbers.forEach((num: string) => {
    if (winningNumbers.includes(num)) {
      hits++;
    }
  });
  return hits;
};

const increaseCardAmount = (cards: any[], index: number, hits: number): any[] => {
    for(let i = index + 1; i < index + hits + 1; i++) {
        cards[i][3]++;
    }
    return cards;
}

const part1 = (cards: any[]) => {
  let sum = 0;
  cards.forEach((card) => {
      const hits = calcHits(card);
      if (hits != 0) {
          let score = 1;
          for (let i = 0; i < hits - 1; i++) {
              score *= 2;
          }
          sum += score;
      }
  });
  console.log(sum);
};

const part2 = (cards: any[]) => {
    for(let i = 0; i < cards.length; i++) {

        const hits = calcHits(cards[i]);

        for(let x = 0; x < cards[i][3]; x++) {
            cards = increaseCardAmount(cards, i, hits);
        }
    }

    let sum = 0;
    cards.forEach((card) => {
        sum += card[3];
    });
    console.log(sum);
};

part1(parseToCards(splitOnNewLine(getRawInput('day04'))));
part2(parseToCards(splitOnNewLine(getRawInput('day04'))));