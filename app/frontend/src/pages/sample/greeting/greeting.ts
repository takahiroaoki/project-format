import { EventSetting, ViewModel } from "@src/utils/view-model"
import yup from "@src/utils/yup.custom"
import { globalSchema } from "@src/utils/schema"

const schema = globalSchema.pick(['sampleInput'])

export const EVENT_SAMPLE_CLICK = 'custom_sample_click'

export type State = yup.InferType<typeof schema> & {
    bold: boolean
}

export class Greeting extends ViewModel<State> {
    protected getEventSettings(): EventSetting[] {
        return [
            {
                selector: '.greeting__message',
                eventName: 'click',
                callback: () => {
                    window.alert('clicked!')
                    this.setState({
                        bold: !this.getState().bold
                    })
                    this.emit(EVENT_SAMPLE_CLICK)
                }
            },
        ]
    }

    protected render(): void {
        const { bold } = this.getState()
        this.select('.greeting__message')?.classList.toggle('greeting__message--bold', bold)
    }
}