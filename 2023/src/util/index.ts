import * as fs from 'fs';
import * as path from 'path';

const getRawInput = (day: string): string => {
  const inputPath = path.join(__dirname, `../${day}/input.txt`);

  try {
    return fs.readFileSync(inputPath, 'utf-8');
  } catch (error) {
    console.error(`Error reading input for ${day}:`, error);
    return '';
  }
};

const splitOnNewLine = (input: string): string[] => {
  return input.split('\n');
};

const splitOnWhitespace = (input: string): string[] => {
  return input.split(/\n\n/);
};

const day3Offsets = [
  [
    [-1, -1],
    [-1, 0],
    [-1, 1],
    [0, -1],
    [0, 1],
    [1, -1],
    [1, 0],
    [1, 1],
  ],
  [
    [-1, -1],
    [-1, 0],
    [-1, 1],
    [-1, 2],
    [0, -1],
    [0, 2],
    [1, -1],
    [1, 0],
    [1, 1],
    [1, 2],
  ],
  [
    [-1, -1],
    [-1, 0],
    [-1, 1],
    [-1, 2],
    [-1, 3],
    [0, -1],
    [0, 3],
    [1, -1],
    [1, 0],
    [1, 1],
    [1, 2],
    [1, 3],
  ],
];

export { getRawInput, splitOnNewLine, splitOnWhitespace, day3Offsets };
