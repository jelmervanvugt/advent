import { getRawInput, splitOnNewLine } from '../util';

const extrapolate = (numbers: number[], part2: boolean): number => {
  if (numbers.every((num) => num === 0)) {
    return 0;
  }

  const next: number[] = [];

  for (let i = 0; i < numbers.length - 1; i++) {
    next.push(numbers[i + 1] - numbers[i]);
  }

  if (part2) {
    return next[0] - extrapolate(next, part2);
  }
  return extrapolate(next, part2) + next[next.length - 1];
};

const execPart = (part2: boolean): number => {
  const input = splitOnNewLine(getRawInput('day09')).map((line) => line.split(' ').map((num) => parseInt(num)));
  let total = 0;

  input.forEach((range) => {
    if (part2) {
      total += range[0] - extrapolate(range, part2);
    } else {
      total += extrapolate(range, part2) + range[range.length - 1];
    }
  });

  return total;
};

const part1 = () => {
  console.log(execPart(false));
};

const part2 = () => {
  console.log(execPart(true));
};

part1();
part2();
