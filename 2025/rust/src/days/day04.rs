struct Roll {
    x: i64,
    y: i64,
    marked: bool,
    active: bool,
}

pub fn generate_grid(input: String, grid_size: i64) -> Vec<Roll> {
    let parsed = input
        .lines()
        .map(|r| r.chars().collect::<Vec<char>>())
        .collect::<Vec<Vec<char>>>();
    let mut grid: Vec<Roll> = Vec::new();
    for y in 0..grid_size {
        for x in 0..grid_size {
            if parsed[y as usize][x as usize] == '@' {
                grid.push(Roll {
                    x: x,
                    y: y,
                    marked: false,
                    active: true,
                });
            }
        }
    }
    grid
}

pub fn generate_neighbours(x: i64, y: i64) -> Vec<Roll> {
    let mut neighbours: Vec<Roll> = Vec::new();
    neighbours.push(Roll {
        x: x - 1,
        y: y - 1,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x,
        y: y - 1,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x + 1,
        y: y - 1,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x - 1,
        y: y,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x + 1,
        y: y,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x - 1,
        y: y + 1,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x,
        y: y + 1,
        marked: false,
        active: true,
    });
    neighbours.push(Roll {
        x: x + 1,
        y: y + 1,
        marked: false,
        active: true,
    });
    neighbours
}

pub fn find_neighbours(index: usize, grid_size: i64, grid: &mut Vec<Roll>) -> i64 {
    let source = &grid[index];
    let mut neighbours: Vec<Roll> = generate_neighbours(source.x, source.y);
    let mut neighbour_amount = 0;
    neighbours.iter().for_each(|neighbour| {
        if (0..grid_size).contains(&neighbour.x)
            && (0..grid_size).contains(&neighbour.y)
            && grid
                .iter()
                .any(|r| r.x == neighbour.x && r.y == neighbour.y && r.active == true)
        {
            neighbour_amount += 1;
        }
    });
    neighbour_amount
}

pub fn pt1(grid: &mut Vec<Roll>, grid_size: i64) -> i64 {
    for i in 0..grid.len() {
        if find_neighbours(i, grid_size, grid) < 4 {
            grid[i].marked = true;
        }
    }
    grid.iter().filter(|r| r.marked).count() as i64
}

pub fn pt2(grid: &mut Vec<Roll>, grid_size: i64) -> i64 {
    loop {
        for i in 0..grid.len() {
            if grid[i].active && find_neighbours(i, grid_size, grid) < 4 {
                grid[i].marked = true;
            }
        }

        let marked = grid.iter().filter(|r| r.marked).count() as i64;
        if marked == 0 {
            break;
        }
        for roll in grid.iter_mut() {
            if roll.marked {
                roll.active = false;
                roll.marked = false;
            }
        }
    }
    grid.iter().filter(|r| r.active == false).count() as i64
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils;

    #[test]
    fn test_pt1_example() {
        let input = utils::load_input_file("day04/example_input");
        let grid_size = input.lines().count() as i64;
        let mut grid = generate_grid(input, grid_size);
        assert_eq!(pt1(&mut grid, grid_size), 13);
    }

    #[test]
    fn test_pt1() {
        let input = utils::load_input_file("day04/input");
        let grid_size = input.lines().count() as i64;
        let mut grid = generate_grid(input, grid_size);
        assert_eq!(pt1(&mut grid, grid_size), 1464);
    }

    #[test]
    fn test_pt2_example() {
        let input = utils::load_input_file("day04/example_input");
        let grid_size = input.lines().count() as i64;
        let mut grid = generate_grid(input, grid_size);
        assert_eq!(pt2(&mut grid, grid_size), 43);
    }

    #[test]
    fn test_pt2() {
        let input = utils::load_input_file("day04/input");
        let grid_size = input.lines().count() as i64;
        let mut grid = generate_grid(input, grid_size);
        assert_eq!(pt2(&mut grid, grid_size), 8409);
    }
}
