import { getRawInput, splitOnWhitespace } from '../util';

const getSeeds = (input: string): number[] => {
  return input
    .split(': ')[1]
    .split(' ')
    .map((el) => parseInt(el));
};

const getMaps = (input: string[]): number[][][] => {
  const maps: number[][][] = [];
  input.forEach((el: any) => {
    const map: number[][] = [];
    el = el.split(/\n/);
    for (let i = 1; i < el.length; i++) {
      map.push(el[i].split(' ').map((el: string) => parseInt(el)));
    }
    maps.push(map);
  });
  return maps;
};

const mapSeeds = (seeds: number[], maps: number[][][]): number[] => {
  return seeds.map((seed: number) => {
    mapsLoop: for (const mapEntry of maps) {
      for (const map of mapEntry) {
        const destination = map[0];
        const source = map[1];
        const range = map[2];

        if (seed >= source && seed <= source + range) {
          seed = destination + (seed - source);
          continue mapsLoop;
        }
      }
    }
    return seed;
  });
};

function generateSeedRanges(seeds: number[]): number[] {
  const result: number[] = [];

  for (let i = 0; i < seeds.length; i += 2) {
    const start = seeds[i];
    const range = seeds[i + 1];

    for (let j = 0; j < range; j++) {
      result.push(start + j);
    }
  }
  return result;
}

function removeDuplicates(array: number[]): number[] {
  const seen = new Set();
  const result = [];

  for (let i = 0; i < array.length; i++) {
    const value = array[i];
    if (!seen.has(value)) {
      seen.add(value);
      result.push(array[i]);
    }
  }

  return result;
}

const part1 = (input: any) => {
  input = splitOnWhitespace(input);

  let seeds = getSeeds(input[0]);
  const maps = getMaps(input.slice(1, input.length));
  seeds = mapSeeds(seeds, maps);

  console.log(Math.min(...seeds));
};

const part2 = (input: any) => {
  input = splitOnWhitespace(input);
  let seeds = getSeeds(input[0]);
  seeds = removeDuplicates(generateSeedRanges(seeds));
  const maps = getMaps(input.slice(1, input.length));
  seeds = mapSeeds(seeds, maps);
  console.log(Math.min(...seeds));
};

part1(getRawInput('day05'));
part2(getRawInput('day05'));
