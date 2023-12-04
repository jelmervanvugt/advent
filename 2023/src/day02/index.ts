import { getRawInput, splitOnNewLine } from '../util';

const parseGames = (input: string[]): any[] => {
  let games: any[] = [];
  input.forEach((game) => {
    const sets: any[] = [];
    game
      .split(': ')[1]
      .split(';')
      .forEach((set) => {
        const entries: any[] = [];
        set.split(', ').forEach((el) => {
          const values = el.trim().split(' ');
          entries.push({ [values[1]]: values[0] });
        });
        sets.push(entries);
      });
    games.push(sets);
  });
  return games;
};

const part1 = (rawInput: string) => {
  const games: any[][][] = parseGames(splitOnNewLine(rawInput));
  let gameAmount = 0;

  for (let i = 0; i < games.length; i++) {
    let gameContainsExceedingValues = false;

    for (const set of games[i]) {
      for (const entry of set) {
        const color = Object.keys(entry)[0];
        const value = parseInt(entry[color]);

        if ((color === 'red' && value > 12) || (color === 'green' && value > 13) || (color === 'blue' && value > 14)) {
          gameContainsExceedingValues = true;
          break;
        }
      }
      if (gameContainsExceedingValues) {
        break;
      }
    }

    if (!gameContainsExceedingValues) {
      gameAmount += i + 1;
    }
  }
  console.log(gameAmount);
};

const part2 = (rawInput: string) => {
  const games: any[][][] = parseGames(splitOnNewLine(rawInput));
  let gameAmount = 0;

  for (let i = 0; i < games.length; i++) {
    let blue = 0;
    let red = 0;
    let green = 0;

    for (const set of games[i]) {
      for (const entry of set) {
        const color = Object.keys(entry)[0];
        const value = parseInt(entry[color]);

        if (color == 'blue' && value > blue) blue = value;
        if (color == 'red' && value > red) red = value;
        if (color == 'green' && value > green) green = value;
      }
    }
    gameAmount += blue * red * green;
  }
  console.log(gameAmount);
};

part1(getRawInput('day02'));
part2(getRawInput('day02'));
