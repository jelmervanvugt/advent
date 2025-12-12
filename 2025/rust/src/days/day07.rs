use std::collections::HashMap;

struct Point {
    x: i64,
    y: i64,
}

impl Point {
    fn new(x: i64, y: i64) -> Self {
        Self { x, y }
    }
}

struct Grid {
    beams: Vec<Point>,
    splitters: Vec<Point>,
    size: i64,
}

impl Grid {
    fn new(beams: Vec<Point>, splitters: Vec<Point>, size: i64) -> Self {
        Self {
            beams,
            splitters,
            size,
        }
    }
}

fn solve(grid: &mut Grid) -> i64 {
    let mut split = 0;
    let mut y = 0;

    loop {
        if y == grid.size {
            break;
        }

        let mut new_beams: Vec<Point> = Vec::new();

        for beam in &grid.beams {
            if grid
                .splitters
                .iter()
                .any(|splitter| splitter.x == beam.x && splitter.y == beam.y + 1)
            {
                let new_beam_left = Point::new(beam.x - 1, beam.y + 1);
                let new_beam_right = Point::new(beam.x + 1, beam.y + 1);

                if !new_beams
                    .iter()
                    .any(|beam| beam.x == new_beam_left.x && beam.y == new_beam_left.y)
                {
                    new_beams.push(new_beam_left);
                }

                if !new_beams
                    .iter()
                    .any(|beam| beam.x == new_beam_right.x && beam.y == new_beam_right.y)
                {
                    new_beams.push(new_beam_right);
                }

                split += 1;
            } else {
                let new_beam_below = Point::new(beam.x, beam.y + 1);
                if !new_beams
                    .iter()
                    .any(|beam| beam.x == new_beam_below.x && beam.y == new_beam_below.y)
                {
                    new_beams.push(new_beam_below);
                }
            }
        }

        grid.beams.clear();
        grid.beams.append(&mut new_beams);
        y += 1;
    }

    split
}

fn find_paths(x: i64, y: i64, grid: &Grid, memo: &mut HashMap<(i64, i64), i64>) -> i64 {
    if y == grid.size {
        return 1;
    }

    // Check of we dit al berekend hebben
    if let Some(&result) = memo.get(&(x, y)) {
        return result;
    }

    let splitter = grid.splitters.iter().any(|s| s.x == x && s.y == y + 1);
    let result = if splitter {
        find_paths(x - 1, y + 1, grid, memo) + find_paths(x + 1, y + 1, grid, memo)
    } else {
        find_paths(x, y + 1, grid, memo)
    };

    memo.insert((x, y), result);
    result
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils::load_input_file;

    #[test]
    fn test_pt1_example() {
        let mut grid = parse_grid("day07/example_input");
        assert_eq!(solve(&mut grid), 21);
    }

    #[test]
    fn test_pt1() {
        let mut grid = parse_grid("day07/input");
        assert_eq!(solve(&mut grid), 21);
    }

    #[test]
    fn test_pt2_example() {
        let mut grid = parse_grid("day07/example_input");
        let mut memo = HashMap::new();
        assert_eq!(
            find_paths(grid.beams[0].x, grid.beams[0].y, &mut grid, &mut memo),
            40
        );
    }

    #[test]
    fn test_pt2() {
        let mut grid = parse_grid("day07/input");
        let mut memo = HashMap::new();
        assert_eq!(
            find_paths(grid.beams[0].x, grid.beams[0].y, &mut grid, &mut memo),
            40
        );
    }

    fn parse_grid(file: &str) -> Grid {
        let input = load_input_file(file)
            .split("\n")
            .map(|line| line.chars().collect())
            .collect::<Vec<Vec<char>>>();
        let mut splitters: Vec<Point> = Vec::new();
        let mut beams: Vec<Point> = Vec::new();

        for y in 0..input.len() {
            for x in 0..input[y].len() {
                if input[y][x] == '^' {
                    splitters.push(Point::new(x as i64, y as i64));
                } else if input[y][x] == 'S' {
                    beams.push(Point::new(x as i64, y as i64));
                }
            }
        }
        Grid::new(beams, splitters, input.len() as i64)
    }
}
