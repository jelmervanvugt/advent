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

export { getRawInput, splitOnNewLine };
