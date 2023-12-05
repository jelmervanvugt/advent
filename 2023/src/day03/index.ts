import { getRawInput, splitOnNewLine, day3Offsets } from '../util';

const findSymbols = (rows: string[], part2: boolean): string[] => {
  const locations: string[] = [];
  let pattern = /[^\d.]/;

  if (part2) {
    pattern = /\*/;
  }

  for (let y = 0; y < rows.length; y++) {
    for (let x = 0; x < rows[y].length; x++) {
      if (pattern.test(rows[y].charAt(x))) {
        locations.push(`${y}-${x}`);
      }
    }
  }
  return locations;
};

const findNumbers = (rows: string[]): any[] => {
  const numbers: any[] = [];

  for (let y = 0; y < rows.length; y++) {
    const row = rows[y];
    let x = 0;

    while (x < row.length) {
      const char = row.charAt(x);
      const pattern = /\d/;

      if (pattern.test(char)) {
        let number: string = char;
        let nDigits = 1;
        let nextChar = row.charAt(x + 1);

        while (pattern.test(nextChar)) {
          number += nextChar;
          nDigits++;
          nextChar = row.charAt(x + nDigits);
        }

        numbers.push([y, x, parseInt(number)]);
        x += nDigits;
      } else {
        x++;
      }
    }
  }

  return numbers;
};

const isAdjacent = (symbols: string[], number: number[]): boolean => {
  const [y, x, value] = number;

  const numDigits = value.toString().length;
  const adjacentSpots = day3Offsets[numDigits - 1];

  for (const coordinates of adjacentSpots) {
    const spot = `${y + coordinates[0]}-${x + coordinates[1]}`;
    if (symbols.includes(spot)) {
      return true;
    }
  }
  return false;
};

const getNumberFromIndex = (y: number, x: number, numbers: any[]): number => {
  for (const [numY, numX, numberValue] of numbers) {
    if (y === numY && x >= numX && x < numX + numberValue.toString().length) {
      return numberValue;
    }
  }
  return 0;
};

const isGear = (symbol: string, rows: string[], numbers: any[]): number => {
  const pattern = /\d/;
  const adjacentSpots = day3Offsets[0];
  const symbolCoordinates = symbol.split('-').map((coordinate) => parseInt(coordinate));
  let firstFind = undefined;

  for (const coordinates of adjacentSpots) {
    const y = symbolCoordinates[0] + coordinates[0];
    const x = symbolCoordinates[1] + coordinates[1];

    if (pattern.test(rows[y][x])) {
      if (firstFind == undefined) {
        firstFind = getNumberFromIndex(y, x, numbers);
      } else {
        return firstFind * getNumberFromIndex(y, x, numbers);
      }
    }
  }
  return 0;
};

const part1 = (rawInput: string) => {
  const rows = splitOnNewLine(rawInput);
  const symbolLocations = findSymbols(rows, false);
  const numberLocations = findNumbers(rows);

  let sum = 0;

  numberLocations.forEach((number) => {
    if (isAdjacent(symbolLocations, number)) {
      sum += number[2];
    }
  });

  console.log(sum);
};

const part2 = (rawInput: string) => {
  const rows = splitOnNewLine(rawInput);
  const symbolLocations = findSymbols(rows, true);
  const numberLocations = findNumbers(rows);
  let sum = 0;

  symbolLocations.forEach((symbol) => {
    sum += isGear(symbol, rows, numberLocations);
  });

  console.log(sum);
};

part1(getRawInput('day03'));
part2(getRawInput('day03'));
