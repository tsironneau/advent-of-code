package y2021.day10;

class Input {

    public static final String INPUT = """
            {<<{({[(<<((({<>{}}(<>[]))<{<>{}}<[][]>>)([[[][]]][{{}<>}]))>{(([<{}>{{}<>]][[<>()](<><>)]))<<<[[][]][[]
            {[([{[[[{({<<[<>[]]<()[]>>(<[]<>>({}))><{[{}()]{<>{}}}<<{}{}>{{}[]}>>}<<{{<>()}<()()>}<[(){}]<[]{
            <(((<([(<(<<[<[][]><{}{}>]>>)><(({[({}[])<[]()>][<[]()><{}{}>]}({[{}<>][{}()]}{({}[])}))(<[{{}[]
            {{<[[([{({(<[{()()}{{}<>}]({()()})>){<({[]<>}({}())){(()<>)<[]<>>}><({()[]}{[]}){{{}{}}}>}})}<[[((([<>()
            [[({<<([[(({([()[]]{()<>})<{<>[]}[{}()]>}({{[]()}{[][]}}<<<>{}>{()[]}>))[(([(){}])<<(){}>{<>
            ({{{{[{[{[{<(({}<>)([][])){[{}{}]([]())}>}{{[[{}]{[]()}]}<([<><>][<>[]])>}]}]}<(<<{(<{[]()}{<><>}>(
            {({[((({(<[[({[]()}<[]<>>)<{{}()}{<>{}}>]][{([<>()]<<>()>)[{<>{}}[[]()]]}{([{}()]<<>[]>){(()<>){[]}}}]>)(({<<
            {<<[<{{([<<{(((){})([][]))}(((()[])<()()>)<<<>{}><(){}>>)>>[({<<<>()>{()<>}><<[]()><<>{}>>})[({{
            (<<[(<{{<[[((<{}()>{[][]})<{{}()}(()<>)>)][[{(<><>}<<>()>}[{{}{}}({}())]]]]>([{<<[{}<>]{{}}>{
            <{<(<({{([<[[[[]{}]<<>()>]][<<<><>>({}{})>]>[[{[[][]][()()]}<[()()]<<>{}>>](([{}()]])]]<<[{<<><>>{{}
            {([[{([[{[([[{[]<>}{{}[]}](([]{})[[]<>])])]{[({([]())[{}<>]}[<{}><<><>>]){{[{}{}]{<><>}}({<>})}]
            <<(([{{[<<<{({()()}{<>()}){<()()><<>()>}}<<([]<>)[<><>]><((){})[<>()}>>>{<(([]<>){{}<>})>{<[[][]][{}[]]
            {{<(([[({{(<({<>()}<<>[]>)(({}[])(<>))>{([<>()][[]()])<[<>[]]{{}{}}>})<(([<>[]][<>{}]){{()<>}(<>())})
            ({<{<({[{{[<<{{}{}}(<>[])><<[]{}>>>]{{[[()[]][{}()]]{<<>{}>([]<>)}}{[{<>{}}(<>())]}}}}{((((<{}{}>{[
            {<[{[<[<{(([{{[]{}}<[]()>}[[<><>][<>{}]]]{[((){}){{}[]}]{(()()){[]{}}}}))(<({{{}()}{{}<>}}{{<
            <[<{([{{([({{[[]{}]([]<>)}(<<>[]>[<>[]])}<(<{}{}>(()[]))<{(){}}(<>())}>)]((<<[()[]]([][])>>[<([][]){()
            {([{<{{(<{<<({(){}}[<>[]]]<<[][]>({}{})>>><[{{[]()}<()<>>}[<[]<>><<><>>]]<[<()>{()<>}]>>}<({([{}[]][<
            {<{[({<(<({[<(<>[])>{({}[])([]{})}][[[<>{}]](<[]()>((){}))]>{[(({}<>)[{}[]])([{}]{[]()})]})>
            [{[({[(<((<(({[]{}}<()()>)({{}}<(){}>))>([{{{}[]}(<>())}(<{}{}>{<>[]})][{<{}[]>{{}[]}}([<><>][{}{}])]))[{(([
            (({((([(<({{[<<>[]><[][]>]((())(<>{}))}})<{(<<[]{}>[<>{}]><[()()]{<>()}>)[<[<>][()<>]>[[()()]{(){
            ([([[({({{<<({[][]}(<>{}))<{[]{}}{(){}}>>{({<>()}[[]()])}>([{<()[]>[<>[]]}]({<(){}}(<><>)}[<{}[]>{<>{}}])
            ({([{[<[({<<[({}{}){[]()}>[[<>{}]<()()>]>>[<{[<>()][{}()]}<[()<>]{()<>}>>]}<<{(<<>{}>)}{[[<>()]
            [{(({({[{[<{<[<><>]>{{{}<>}[()<>]}}>]{{<[(<>)[()()]]<<<><>>({}<>)>><[(()[])[(){}]][<[]{}>]>}{{<({}{
            {([<[(((([({([<>{}]<[]()>)[[(){}]]}((<[]>(<>())){([])(()())}))]){<(<(<(){}](<><>))<(()[]){<>[]}>>)[(({[]{
            {{(<[([[[{{{(<[]{}>(()()))[{[][]}[{}()]]}<(<()<>>(<><>))>}<<<<[]{}>{[][]}><[<><>]{[]()}>><<[()<>){<>{}}>[<()(
            <{{(<<[{[<<<{({}[])(<><>)}{[()]{{}<>}}>[{<<>{}>{[][]}}<[<>]({}()))]><({({}[])((){})}([{}{}][{}<>]))[
            [<{<<[({({<(<<{}>([]<>)>[{[]()}[[]{}]]){[(<>[])({}[])][{()<>}[{}{}>]}><<([<>{}]<()[]>)<{()
            ({(<<<<{{{[<<[<>]((){})>[<[]<>>((){})]>([<[]()><<>[]>}[<<><>>((){})])][({{{}[]}((){})})[({()<>}{<>})[{()[]}
            <{{{(<<<((<([{[]{}}{{}}])[[(()<>)(<>{})]]>)<<[[<[]{}><[]{}>](<()()>{<>[]})][(([]<>)<{}[]>)(<{}()><<
            [<{(<[{<(<<((((){}][<>[]]){[[]()]<{}{}>})<[[()()]{{}()}]<[[]{}]{<>{}}>>>>[<(({{}<>}{{}[]}){(()(
            [<({<[[({[<<{(()())[[]]}((()())<<>[]>)>([[<><>][{}()]]<<(){}>{<>}>>><{{<[][]>}[{{}[]}{<>[]}]}>
            {{[<<({[((<<[(<>{}]<()<>>]><[({}<>){[]{}}][{{}{}}((){})]>>{{[[<>[]]]<{[][]}>}}))]}){([([{[{<
            (<[[<[<{<<<[({{}[]}{<>[]})][<<{}{}>{<><>}>[{{}{}}<()<>>]]>>><{([[(<>())][(()<>)(()[])]])}>}>{{{<[((
            ([{[({[{<{[({{<>()}}<[[]{}]{<>{}}>)][{{[[][]]<<>{}>}((<><>)<()<>>)}]}>}]{<<[[[<{<>[]}[{}[]
            [[<{[[<<{<{<{([][])[{}[]]}[[[]<>]{<><>}]>[[[<>][<>{}]]{([]()>[<>[]]}]}<(<<()[]><{}<>>>{<{}{}>({}<
            <(((<<{{({{(((()())[{}()])<(()())[()<>]>)}<[<{<>[]}({}{})><{[]{}}<[]<>>>](<({}())<<>[]>>{[<>]})>})}
            (<<[({[{{<[<<<{}()><[]{}>>([<><>][[][]])>{{(()[])(()<>)}<[()<>][<>]>}]<[{([][])<{}{}>}[{[]<>}]][<{{}()}<[
            [([([({{{[<[([(){}]<[]()>)<{()()}[()<>]>][[[(){}]<()[]>][{[]()}{{}<>}])>{([{{}{}}(<>())][{{}
            ({<<<(<[<([([{<>[])[(){}]])([[<>()]{[]<>}](<[][]>))]([{[{}()]}]<[[{}()]<{}()>][<{}<>><(){}>]>))><({[[{(){}
            {{<(<<(<[[{[{[<>()][<>{}]}{({}[]){[]{}}}]}<<<({}{})([][])>({()()](<><>))>{<([]())((){})>{{[][]}{{}()}}}>]][{
            [{(<(((<<{{<[{{}{}}<()()>]>((([][])<{}[]>)<[<>{}]{<>()}>)}}<[(<({}{})<[]()>>[(<><>){[]<>}]){({<>{}}<()()>)
            <[<<[([[<{[<<[()[]][{}{}]><(<><>){(){}}>>({<[]()>{()()}}<(()[]){()()}>}]{([<[][]>](([]<>)[{}<>]))
            (<([[[<((<(({<{}<>>[{}()]}{<<><>>}))})([[[[{{}()}<()>]<<()<>>{<>[]}>]]((({{}[]}([]{}))({{}()
            [(([{({<([{[<{<>()}[[][]]>[{<>}<{}{}>]]({({}[]){<>[]}})}([{(()<>)<<>{}>}[[[]{}]{()[]}]][([[]<>][<><>])[<[]{
            [<<({{{([<<({{<><>}[{}{}]})[<[()]({}{})>(<{}()>[[]{}])]>{(([{}{}][<><>])[[{}<>]{[]{}}>)}>][<[([{()()}<{}<>>]<
            <[{({<([([(<{[[][]](<>())}([{}()]([]{}))>((<()()>[()<>])<([]{}){()[]}>))[<(({}{})(()<>))(([]()][{}<>])><
            {{{[{<({<<{[(([]())<[][]>][{{}[]}{[]()}]]{[{()<>}<()[]>]([<>()][<>()])}}>>}({[(((<{}[]>[<><>])<{[]{}
            <<(<<<(<[{{{{{<>[]>[[]<>]}{[<>[]](<>{})}}}}][(<<({[]<>}<()()>)<{{}()}[{}{}]>><[<<>{}><<>()>]([<
            [{<(((<{{[{[[[{}<>][[][]]][{[]{}}{[]()}]](([(){}]([]<>))[<()()>[[]{}]])}]}<(([{(<>[])[{}<>]}([<>]{[]<>
            <(<{[([(<[([<{(){}}>([<>[]]([]{}))]){{({{}[]}{()[]})<<{}{}>{[]{}}>}{{{[][]}{{}[]}}{<[]{}>{(){}}}}
            <<[{(<([{<<<({(){}})([()]<[][]>)>>>}])>[(<[<[<{(<><>)([]<>)}{([]{})<{}{}>}>][[(({}{})(<>{})){{{}{}}({}<>)}]<[
            [[([[[{[{([[{<[]<>>(()<>)}<{<><>}({}<>)>][[<()()>[()<>]](([]{})({}[]))]])([((<(){}>(()[])){{<>}[<>()]})]<
            [[(<(((({<<(<<[]<>>><((){})<<>{}>>)<[{<><>}([][])]<([]<>)<()<>>>>>>[{[({(){}}{<>{}})]<[({}<
            <[{(({<{(<[[({()<>})([[]{}]{()()})]<(<<>{}>[<>[]>){{{}{}}(<>{})}>]<{[<[][]>]}[[(()[])<[][]>]{[(
            <[{<<(<[[(((([[]{}][<>()]](({})<<>[]>)))[<{[<>{}](()())}[<<><>>[<>()]]><[<<>[]>{<><>}]((()<>){()<>})>])[{{({[
            <{[[<[[[{({<([<>{}]{<>{}})[[{}<>]{{}{}}]>([{[]<>}<[]<>>]{{{}()}})})<<<[[[][]]<[]()>]({{}{}}[<>()]))>>}{
            [{{{{{[{(<{<{{[]{}}((){})}([[]()]({}()))>}{{<[<><>]<[][]>><<{}[]><{}{}>>}<([<>{}]{<>()}){(<>{})}>}>)[
            ([{{[({[{{<{{([]<>)[{}[]]}[[{}{}]{[]()})}>[[<{[]{}}([]{})><(<>{})[{}<>]>]({(<>)[{}[]]}(<[]()>([]<>)))]}
            <[{{[{{{[[[<<<{}<>><{}<>>>{{(){}}({}<>)}>{[[()()]<{}()>]}]((<(<>)(()<>)>{[{}()]<{}[]>})(({[]
            <({{[[[{(([<[<[][]>{[]{}}](<{}{}>{{}[]})>])((({<<>[]>}[([][])<()<>>]})[<{[[]]{{}()}}{{(){}}{[]()}}><{(<>{}){[
            <(((<(<<<{<[[<{}<>>[<>{}]]([[]<>]<{}()>)]>({({()<>}({}{}]){{(){}}<<>[]>}}[{[()<>]}<(()<>)(
            ({{<{[<<([({<{()<>}>(<{}[]>[()<>])}<[([][])[()]]>)(<([()()][{}{}])[([]())[{}[]]]>{[(<>{})]})]{<([<[]<>
            ({[(((({<{({[([]<>)(()<>)]([{}{}](()[]))}[<{()[]}[{}()]>{({}[]){()<>}}]){[[<<><>>[<>[]]](((
            <<{<{[{<{[{<[[{}{}]{<>{}}]([(){}]{<>()})><[<[][]>(<>[])]>}<[{<{}<>>(()[]]}([[][]]([]{}))][{{{}()}
            [<{<{([<([<(({[]<>}(()<>)){{()[]}<[]()>})[{{(){}}{[]<>}}<{[][]}{<>{}}>]>{<{{[]()}}>[{<{}()>[[]()]}(<[]()>[(
            ({(<{{{[[({[[{<><>}{{}<>}]{{<>()}[{}{}]}]{{({}<>)([]<>)}[[(){}]]}}[<{({}<>)({}{})}{[[]]<<>{}>}><{([]{}
            {<[<{<[((<<({<<><>>[()<>]}({{}()}<{}<>>))[{{<>}<<>[]>}[<[][]>({}[])]]>>){<(<<{()}[[]{}]>{[[]]{{}{}}}>
            [<{{([(<[{{<{<[][]>{(){}}}{<()<>>[()[]]}>}{<{[[][]][{}<>]}([()[]]<(){}>)>(({()))(<[]>(()())))}}]>(
            {<{<<(<[[[[([(<><>)]{[[]{}](<>{})})]({({[]<>}<{}[]>)}{[<<>()>{[]()}][{<>{}}[<>[]]]})><[[(<
            <([<[{(<({(([{[][]}[<>{}]](({}())<<>{}>))([([]{})]<<<><>>(<>{})>)){{<<{}[]>{<>[]}><[[]()]<
            ((([<<{[([[{{({}<>)(<><>)}{{[]<>}<<>>}}(({()()}{{}<>}))][{{{(){}}[()<>]}<[()[]][[]<>]>}]][{<
            {{{[<[{({<{([(()[])[()[]]]<[<><>]({}())>)}<{(<<>[]>{()}){<()()>(()[])}}[[[[]{}]({}{})]]>>}<[[<{<(){}>
            ([<(<(<[{[{{<{{}<>}{[][]}>([()[]]([]))}{({[]{}}[()()])}}<<(({}[])[[]{}])[(<><>)]>>][<<([<>(
            (<(<[{[<(([{{<{}>[()[]]}[({}<>){()<>}]}<<{{}[]}{()()}>{(<>{}){()<>}}>]({[(<><>)[{}<>]]<<{}<>><()(
            [({{[{({<([<{([]{})({}{})}{[<>()][<><>]}>[{<<>>(()[])}]]){(({[{}[]]<{}[]>}({<>{}}[()()]))[<<[]<>><[]{}>>])}
            <(([(({[{{{({(<>[])<[]()>}{(()<>)<()()>})}{[<(()<>)[<><>]>(<()()>)]<({{}()}<(){}>)[([]<>)<()[]>]>}}{{{{<{}
            {[<({<<<(({<{([][])<{}<>>}[[[]()][{}[]]]>{[[{}{}](<><>)][([][])[<>()]]}})(([(((){})<<>()>)<{[]<>}<{}>>]<[([]{
            <[[[<[[[<[[({{[]<>}{[]()}}{{{}()}})]{{(<{}[]>[()()])(<[]()}((){}))}}](<{[<()()><{}{}>](<()>{{}()})}
            {[{<{[<{{(<(({{}[]}{[]<>}){[<><>]{{}{}}})>([{{<>{}}<(){}>}]<([[]<>]([]()])(<[]{}>)>))}<({(
            {{<(<(<<<[{<[({}())[{}]](<()<>>{<>[]})><<<<>()>[()[]]]{{[]<>}{{}[]}}>}{[([<>[]]{[]{}})](<{<>()}(
            ({(({{<((({[([(){}][()<>])]((<[]>({}{}))[([][])({}{})])})))>{<<([{[(<>())<{}<>>]}[{{()<>}[{}()]}{<[]{}>[()]}]
            (<<[[(<({((([[[]{}](())]{(<><>)(<>())}))[[{[[]()]<[]<>>}<(()[])<<>[]>>](({{}[]}[{}{}]){{<>{}}{<>
            <[[<(<[({[{<{<{}<>>({}{})}(<()()>{()[]})][(([][]){{}{}})<{[]{}}({}())>]}(<<([]<>){<><>}><[(
            ((([{[{[{{({<[{}{}]<()<>>>{{<>()}(<>{})}}(<{[]{}}[{}[]]>[{()[]}{{}[]}]))}{([[<[]><(){}>]{{{}()}[()[]
            <([<<{[{<<<{<[[]()]<{}[]>>{[{}<>]([][])}}(({{}}<(){}>)[[()<>][[]<>]])>><[({{[]}[{}<>]}[[[]<>]<<>{}>])
            {<{{<<<(<[([{(()())<{}<>>}(<{}<>>([]())}](<{[]<>}(<>{})>))([[<(){}>[[]()]]]{[[()[]]({}())][<{}
            <([{<({({{<<<<(){}>{<><>}>{<<><>>([]())}>((<<>()><{}()>)[({}{})[(){}]]}>{([((){}){[][]}])[<
            {[(<(({[({<({<(){}>([][])}((<>)[()])){<[<>()]<<><>>>}>[<{<<>[]>}<{{}()}[(){}]>>[[<[]<>>{{}[]}>]]}<[<<<{}
            {{<({<([<<(([{()}{{}<>}][{<><>}{[]{}}]))<(<<()<>><[]{}>]{(()[]){()[]}})[[(<>){{}()}]{((){})}]>>
            <[<[<[[({<(({{[][]}[()()]}{(<>{}]{<>[]}})<<(()())(()<>)>>)>(({[(<>{})[{}[]]]{([][])[()[]]}}{<""";
}
