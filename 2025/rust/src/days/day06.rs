enum Operation {
    ADDITION,
    MULTIPLY,
}

struct Problem {
    numbers: Vec<i64>,
    operation: Operation,
}

impl Problem {
    fn new() -> Self {
        let numbers: Vec<i64> = Vec::new();
        let operation = Operation::ADDITION;
        Problem { numbers, operation }
    }

    fn solve(&self) -> i64 {
        match self.operation {
            Operation::ADDITION => self.numbers.iter().sum(),
            Operation::MULTIPLY => self.numbers.iter().product(),
        }
    }
}

fn solve(problems: Vec<Problem>) -> i64 {
    problems.iter().map(Problem::solve).sum()
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils::load_input_file;

    #[test]
    fn test_pt1_example() {
        let problems: Vec<Problem> = parse_input("day06/example_input");
        assert_eq!(solve(problems), 4277556);
    }

    #[test]
    fn test_pt1() {
        let problems: Vec<Problem> = parse_input("day06/input");
        assert_eq!(solve(problems), 4951502530386);
    }

    #[test]
    fn test_pt2_example() {
        let problems: Vec<Problem> = parse_input_2("day06/example_input");
        assert_eq!(solve(problems), 3263827);
    }

    #[test]
    fn test_pt2() {
        let problems: Vec<Problem> = parse_input_2("day06/input");
        assert_eq!(solve(problems), 4951502530386);
    }

    fn parse_input(filename: &str) -> Vec<Problem> {
        let mut problems: Vec<Problem> = Vec::new();
        let input1 = load_input_file(filename);
        let input: Vec<Vec<&str>> = input1
            .split("\n")
            .map(|line| line.split_whitespace().collect())
            .collect();

        for y in 0..input.len() {
            for x in 0..input[y].len() {
                if y == 0 {
                    problems.push(Problem::new());
                }
                if input[y][x] == "*" {
                    problems[x].operation = Operation::MULTIPLY;
                } else if input[y][x] == "+" {
                    problems[x].operation = Operation::ADDITION;
                } else {
                    problems[x]
                        .numbers
                        .push(input[y][x].parse::<i64>().unwrap());
                }
            }
        }
        problems
    }

    fn parse_input_2(filename: &str) -> Vec<Problem> {
        let mut problems: Vec<Problem> = Vec::new();
        let input1 = load_input_file(filename);
        let input: Vec<Vec<char>> = input1.lines().map(|line| line.chars().collect()).collect();

        let mut problem = Problem::new();

        for x in 0..input[0].len() {
            if input[0][x].is_whitespace()
                && input[1][x].is_whitespace()
                && input[2][x].is_whitespace()
                && input[3][x].is_whitespace()
            {
                problems.push(problem);
                problem = Problem::new();
            } else {
                if !input[4][x].is_whitespace() {
                    problem = Problem::new();
                    if input[4][x] == '*' {
                        problem.operation = Operation::MULTIPLY;
                    } else {
                        problem.operation = Operation::ADDITION;
                    }
                }

                let number_string = [input[0][x], input[1][x], input[2][x], input[3][x]]
                    .iter()
                    .collect::<String>();
                let trimmed = number_string.trim();
                let number = trimmed.parse::<i64>().unwrap();
                problem.numbers.push(number);
            }
        }
        problems.push(problem);

        problems
    }
}
