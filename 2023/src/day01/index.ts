import { getRawInput, splitOnNewLine } from '../util';

const calcScore = (input: string[]): number => {
  return input
    .map((el) => {
      return el.replace(/[^0-9]/g, '');
    })
    .map((el) => {
      if (el.length == 1) {
        return el + el;
      }
      const numbers = el.split('');
      return numbers[0] + numbers[numbers.length - 1];
    })
    .map((el) => parseInt(el))
    .reduce((acc, el) => acc + el, 0);
};

const part1 = (rawInput: string) => {
  console.log(calcScore(splitOnNewLine(rawInput)));
};

const part2 = (rawInput: string) => {
  const replaceWords: { [key: string]: string } = {
    one: 'o1e',
    two: 't2o',
    three: 't3e',
    four: 'f4r',
    five: 'f5e',
    six: 's6x',
    seven: 's7n',
    eight: 'e8t',
    nine: 'n9e',
    zero: 'z0o',
  };

  console.log(
    calcScore(
      splitOnNewLine(rawInput).map((line) => {
        return Object.keys(replaceWords).reduce((acc, word) => {
          return acc.replace(new RegExp(word, 'g'), replaceWords[word]);
        }, line);
      })
    )
  );
};

part1(getRawInput('day01'));
part2(getRawInput('day01'));
