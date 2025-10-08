struct State {
    dial: i32,
    exactly_zero: i32,
    passed_zero: i32,
}

pub fn pt1(input: String) -> i32 {
    do_rotations(input).exactly_zero
}

pub fn pt2(input: String) -> i32 {
    do_rotations(input).passed_zero
}

fn do_rotations(input: String) -> State {
    let mut state = State {
        dial: 50,
        exactly_zero: 0,
        passed_zero: 0,
    };

    for line in input.lines() {
        let direction = line.chars().next().unwrap();
        let amount = line[1..].parse::<i32>().unwrap();
        let step = if direction == 'R' { 1 } else { -1 };

        for _ in 0..amount {
            state.dial = (state.dial + step).rem_euclid(100);
            if state.dial == 0 {
                state.passed_zero += 1;
            }
        }

        if state.dial == 0 {
            state.exactly_zero += 1;
        }
    }

    state
}

#[cfg(test)]
mod tests {
    use super::*;
    use crate::utils;

    #[test]
    fn test_pt1_example() {
        let input = utils::load_input_file("day01/example_input");
        assert_eq!(pt1(input), 3);
    }

    #[test]
    fn test_pt1() {
        let input = utils::load_input_file("day01/input");
        assert_eq!(pt1(input), 1055);
    }

    #[test]
    fn test_pt2_example() {
        let input = utils::load_input_file("day01/example_input");
        assert_eq!(pt2(input.parse().unwrap()), 6);
    }

    #[test]
    fn test_pt2() {
        let input = utils::load_input_file("day01/input");
        assert_eq!(pt2(input), 6386);
    }
}
