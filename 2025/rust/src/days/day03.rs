pub fn solve(input: String, joltage_length: i64) -> i64 {
    let mut sum: i64 = 0;
    let lines: Vec<&str> = input.lines().collect::<Vec<&str>>();

    lines.iter().for_each(|line| {
        let bank = line
            .chars()
            .map(|c| c.to_string().parse::<i64>().unwrap())
            .collect::<Vec<i64>>();
        sum += find_max_joltage(bank, joltage_length);
    });
    sum
}

pub fn find_max_joltage(bank: Vec<i64>, joltage_length: i64) -> i64 {
    let n = bank.len();
    let target_length = joltage_length as usize;

    if n <= target_length {
        return bank.iter().fold(0i64, |acc, &digit| acc * 10 + digit);
    }

    let mut joltages = Vec::new();
    let mut start = 0;

    for position in 0..target_length {
        let remaining = target_length - position - 1;
        let search_end = n - remaining;

        let mut max_digit = 0;
        let mut max_index = start;

        for i in start..search_end {
            if bank[i] > max_digit {
                max_digit = bank[i];
                max_index = i;
            }
        }

        joltages.push(max_digit);
        start = max_index + 1;
    }

    joltages
        .iter()
        .map(|n| n.to_string())
        .collect::<String>()
        .parse()
        .unwrap()
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils;

    #[test]
    fn test_pt1_example() {
        let input = utils::load_input_file("day03/example_input");
        assert_eq!(solve(input, 2), 357);
    }

    #[test]
    fn test_pt1() {
        let input = utils::load_input_file("day03/input");
        assert_eq!(solve(input, 2), 17427);
    }

    #[test]
    fn test_pt2_example() {
        let input = utils::load_input_file("day03/example_input");
        assert_eq!(solve(input, 12), 3121910778619);
    }

    #[test]
    fn test_pt2() {
        let input = utils::load_input_file("day03/input");
        assert_eq!(solve(input, 12), 173161749617495);
    }
}
