export default function ($sce) {
    "ngInject";

    const MAX_HEADING_LEVEL = 6;

    function replaceHeading(line) {
        let headingLevel;

        if (line.trim().match(new RegExp(`^#{1,${MAX_HEADING_LEVEL}}(?!#)`))) {
            headingLevel = match[0].length;
            line = line.replace(new RegExp(`^#{${headingLevel}}`), "");
            return `<h${headingLevel}>${line}</h${headingLevel}>`;
        } else if (line.trim().match(new RegExp(`^@{1}(?!@)`))) {
            line = line.replace(new RegExp(`^@{1}`), "");
            return `<i>${line}</i>`;
        }
        return line;
    }

    function replaceHeadings(input) {
        return input.split("\n").map(replaceHeading).join("\n");
    }

    function replaceNewLines(input) {
        return input.replace(/\n+?/g, "<br>");
    }

    function transformInput(input) {
        return replaceNewLines(replaceHeadings(input));
    }

    return function (input) {
        return $sce.trustAsHtml(transformInput(input));
    }
}