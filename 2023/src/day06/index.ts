import { getRawInput, splitOnNewLine, splitOnWhitespace } from '../util';

const calculatePossibilities = (races: number[][]): number[] => {
  const possibilities: number[] = [];

  for (let i = 0; i < races[0].length; i++) {
    const time = races[0][i];
    const record = races[1][i];
    let nPossibilities = 0;

    for (let t = 0; t < time + 1; t++) {
      const dist = (time - t) * t;
      if (dist > record) {
        nPossibilities++;
      }
    }
    possibilities.push(nPossibilities);
  }

  return possibilities;
};

const part1 = (input: string[]) => {
  console.log(
    calculatePossibilities(
      input.map((race) =>
        race
          .split(/\s+/)
          .slice(1)
          .map((num) => parseInt(num))
      )
    ).reduce((accumulator, currentValue) => accumulator * currentValue, 1)
  );
};

const part2 = (input: any) => {
  const races = splitOnNewLine(input)
    .map((num) => num.split(':')[1].replace(/\s+/g, ''))
    .map((num) => parseInt(num));
  console.log(calculatePossibilities([[races[0]], [races[1]]]));
};

part1(splitOnNewLine(getRawInput('day06')));
part2(getRawInput('day06'));
