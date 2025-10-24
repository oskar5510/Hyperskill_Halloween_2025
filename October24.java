/*
 * Task: The Game Begins
 *
 * Description:
 * In this task, the mysterious message says: "The clue is in unique letters."
 * The program analyzes a long string of random characters and extracts only
 * the letters that appear exactly once in the entire dataset.
 *
 * Steps:
 * 1. Convert the dataset (a long string of characters) into an array.
 * 2. Count how many times each character appears.
 * 3. Filter only those characters with exactly one occurrence.
 * 4. Combine these unique characters in their original order to form the final clue.
 *
 * Output:
 * A string containing all unique (non-repeating) letters from the dataset.
 */

void main() {
    String dataset = "udkufpjfauyzzulbaoowapalsadjogpfpzsxqzburpfwbxfayfzvxdqxblqdmsjruudjvqxdvgwdrxvqjdumjrzyvlxrfmosdluvufgrwauupwlwzdsbwjxouovirlbbjrwfzmsoxxggrwumsglgquqbsmyofqbbbjvmbvgmlzguqppwmdsmrzgsovbvrlubpudsqgmjsmwfsgmsgysuqmppwzwjjqwaowmfvfravfbadujqqudydwfovsylqlquzwlwjmbuvdfrllumozjolgbbarrglvmoazjqorquuzmltspayfdlomvwrmlblzwybpyvbjxyursqfoqxxjmsspvypalyqjgzmuqrcppajmgsxzvvpamvwdbrxbuldsmbvfoljmqxpdzrpxovbooqfuffxloomlumfwgxpwwljqqqwauvfwswfszfvolsxoyrbgzxpodxgfmmdbywrqswffrsgpyaljmqyyruojgjmuaxdapjxzxxqhlpyyzfybdbuofdrqwadluuudogzmqvrpazfvybygxvaldmsfglmxdomugslwumxzpblodolofgrxuraddgwpyfrbvmzlfbquydsmxylgmjsedzmjmojrrdqlzszmplyozdrwgqaovsjlpymswmmybsumrmquxjjlrofmdfpavsmslzjulraobrgjxfjdfrafzluzuavlwssrfvxxwdgmvgjbfqaplubmdxqqvjdyxzaqxyrgvjsvbdovflvswbxrlbqmvwdddszvjgwmwrapzoljrusvlgaxabggpmdwbymyspxoxqgaoamvfmbzxuduxzmmmblowjbauudylubdbpzldpfzgxowfwxsyzrbazobuwydbrdfxxpvsqqrxvjlmxjgwaaxmovvmqyojzoqblxpbfdlzvuosgpuxqbuluofqobbrrxdmddlrbuumqoflyvmbgfpbpmlmpyjymmwfypooondsvurfrxppparvrvpoblamo";

    char[] datasetCharArray = dataset.toCharArray();

    Map<Character, Integer> characterMap = new LinkedHashMap<>();

    for (char ch : datasetCharArray) {
        if (characterMap.containsKey(ch))
            characterMap.put(ch, characterMap.get(ch) + 1);
        else characterMap.put(ch, 1);
    }

    StringBuilder word = new StringBuilder();

    characterMap.entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Map.Entry::getKey)
            .forEach(word::append);

    System.out.println(word);
}