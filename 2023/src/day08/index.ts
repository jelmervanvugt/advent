import { getRawInput, splitOnNewLine, splitOnWhitespace } from '../util';

const parseMaps = (input: any): { [key: string]: string[] } => {
  const maps: { [key: string]: any } = [];
  splitOnNewLine(input)
    .map((line) => line.replace(/[\s()]+/g, ''))
    .map((map) => {
      const parsedMap = map.split(/[=,]/);
      maps[parsedMap[0]] = [parsedMap[1], parsedMap[2]];
    });
  return maps;
};

const doStep = (currentLocation: string, instruction: string, maps: { [key: string]: string[] }): string =>
  instruction === 'L' ? maps[currentLocation][0] : maps[currentLocation][1];

const part1 = (input: string[]) => {
  const instructions = input[0];
  const maps = parseMaps(input[1]);

  let stepAmount = 0;
  let destinationReached = false;
  let currentLocation = 'AAA';

  destinationLoop: while (!destinationReached) {
    for (let i = 0; i < instructions.length; i++) {
      if (currentLocation === 'ZZZ') {
        destinationReached = true;
        continue destinationLoop;
      }
      currentLocation = doStep(currentLocation, instructions.charAt(i), maps);
      stepAmount++;
    }
  }
  console.log(stepAmount);
};

const part2 = (input: string[]) => {
  const instructions = input[0];
  const maps = parseMaps(input[1]);

  let stepAmount = 0;
  let destinationReached = false;
  const currentLocations = Object.keys(maps).filter((key) => key.endsWith('A'));

  destinationLoop: while (!destinationReached) {
    for (let i = 0; i < instructions.length; i++) {
      destinationReached = true;
      for (let l = 0; l < currentLocations.length; l++) {
        currentLocations[l] = doStep(currentLocations[l], instructions.charAt(i), maps);
        if (currentLocations[l].charAt(currentLocations[l].length - 1) != 'Z') {
          destinationReached = false;
        }
      }
      stepAmount++;
      if (destinationReached) {
        continue destinationLoop;
      }
    }
  }
  console.log(stepAmount);
};

part1(splitOnWhitespace(getRawInput('day08')));
part2(splitOnWhitespace(getRawInput('day08')));
